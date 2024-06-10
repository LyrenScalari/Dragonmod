package dragonmod.util;

import basemod.ClickableUIElement;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import dragonmod.interfaces.*;
import dragonmod.powers.Dragonkin.SacrificePower;
import javassist.CtBehavior;

import java.util.ArrayList;

import static dragonmod.characters.TheJusticar.Enums.THE_JUSTICAR;

public class StigmataManager {
    public static StigmataUI stigmataUI;
    public static boolean renderstigmataui = false;
    public static int Overheal = 0;
    public StigmataManager() {
    }
    public static void onBattleStart() {
        StigmataManager.StigmataField.Stigmata.set(AbstractDungeon.player,0);
    }

    public static int getStigmataTotal() {
        return StigmataField.Stigmata.get(Wiz.Player());
    }

    public static void gainStigmata(int amount) {
        StigmataField.Stigmata.set(Wiz.Player(), getStigmataTotal() + amount);
    }
    public static void spendStigmata(int amount) {
        StigmataField.Stigmata.set(Wiz.Player(), getStigmataTotal() - amount);
    }
    public static void cureHealing(int heal) {
        Overheal = 0;
        int cureHeal = Math.min(getStigmataTotal(), heal);
        int prevHp = Wiz.Player().currentHealth;
        int projectedhp = Math.min(prevHp + cureHeal,Wiz.Player().maxHealth);
        int overheal = heal - (projectedhp - prevHp);
        if (overheal > 0) {
            Overheal = overheal;
            for (AbstractRelic r : AbstractDungeon.player.relics){
                if (r instanceof OnOverheal){
                    ((OnOverheal)r).onOverheal(Overheal);
                }
            }
            for (AbstractCard e : EnchantmentsField.Enchantments.get(Wiz.Player()).group){
                if (e instanceof OnOverheal){
                    ((OnOverheal) e).onOverheal(Overheal);
                }
            }
            for (AbstractPower p : Wiz.Player().powers){
                if (p instanceof OnOverheal){
                    ((OnOverheal) p).onOverheal(Overheal);
                }
            }
            for (AbstractCard c : Wiz.Player().hand.group){
                if (c instanceof OnOverheal){
                    ((OnOverheal) c).onOverheal(Overheal);
                }
            }
        }
        if (cureHeal > 0) {
            spendStigmata(cureHeal);
            Wiz.Player().heal(cureHeal);
            Wiz.applyToSelf(new SacrificePower(AbstractDungeon.player, AbstractDungeon.player, heal));
            for (AbstractRelic r : AbstractDungeon.player.relics){
                if (r instanceof OnCure){
                    ((OnCure)r).OnCureHeal(cureHeal);
                }
            }
        }
    }

    // Render timing and rules

    public static boolean getStigmataRender() {
        if (CardCrawlGame.dungeon != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            if (renderstigmataui) {
                if (stigmataUI == null) {
                    stigmataUI = new StigmataUI(ImageMaster.loadImage("dragonmod/ui/StigmataUI/Stressvfx.png"));
                }
                return true;
            } else if (StigmataField.Stigmata.get(AbstractDungeon.player) > 0 || AbstractDungeon.player.chosenClass == THE_JUSTICAR) {
                renderstigmataui = true;
                if (stigmataUI == null) {
                    stigmataUI = new StigmataUI(ImageMaster.loadImage("dragonmod/ui/StigmataUI/Stressvfx.png"));
                }
                return true;
            }
        }
        return false;
    }

    public static void renderStressCounter(SpriteBatch sb, float current_x) {
        stigmataUI.render(sb, current_x);
    }

    public static void updateStigmataCounter() {
        stigmataUI.update();
    }

    // Stigmata Patches
    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "damage"
    )
    public static class StigmataPostFix{
    @SpireInsertPatch(
            locator = Locator.class,
            localvars = {"damageAmount"}
    )
    public static void StigmataPostFix(AbstractPlayer __instance, DamageInfo info, int damageAmount) {
        if (renderstigmataui) {
            gainStigmata(damageAmount);
        }
        for (AbstractCard field : EnchantmentsField.Enchantments.get(__instance).group) {
            if (field instanceof onLoseHPEnchantment) {
                ((onLoseHPEnchantment) field).EnchantmentOnAttacked(__instance);
            }
        }
    }
}
    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "damage"
    )
    public static class Damaged {
        public Damaged() {
        }
        @SpireInsertPatch(
                locator = Locator.class,
                localvars = {"damageAmount", "hadBlock"}
        )
        public static void Insert(AbstractCreature __instance, DamageInfo info, int damageAmount, @ByRef boolean[] hadBlock) {
            if (!AbstractDungeon.getCurrRoom().isBattleOver) {
                if (info.type != DamageInfo.DamageType.HP_LOSS) {
                    for (AbstractPower p : AbstractDungeon.player.powers) {
                        if (p instanceof ReciveDamageEffect) {
                            ((ReciveDamageEffect) p).onReciveDamage(damageAmount);
                            ((ReciveDamageEffect) p).onReciveDamage(damageAmount);
                        }
                        if (p instanceof ReciveModifyDamageEffect) {
                            damageAmount = ((ReciveModifyDamageEffect) p).onReciveDamage(damageAmount, info);
                        }
                    }
                    for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                        if (c instanceof ReciveDamageEffect) {
                            ((ReciveDamageEffect) c).onReciveDamage(damageAmount);
                        }
                    }
                    for (AbstractCard c : AbstractDungeon.player.hand.group) {
                        if (c instanceof ReciveDamageinHandCard) {
                            ((ReciveDamageinHandCard) c).onReciveDamage(damageAmount, info.type);
                        }
                        if (c instanceof ReciveDamageEffect) {
                            ((ReciveDamageEffect) c).onReciveDamage(damageAmount);
                        }
                    }
                    for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
                        if (c instanceof ReciveDamageEffect) {
                            ((ReciveDamageEffect) c).onReciveDamage(damageAmount);
                        }
                    }
                    for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                        if (c instanceof ReciveDamageEffect) {
                            ((ReciveDamageEffect) c).onReciveDamage(damageAmount);
                        }
                    }
                    for (AbstractCard c : AbstractDungeon.player.limbo.group) {
                        if (c instanceof ReciveDamageEffect) {
                            ((ReciveDamageEffect) c).onReciveDamage(damageAmount);
                        }
                    }
                }
            }
        }
    }
    private static class Locator extends SpireInsertLocator {
        private Locator() {
        }

        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(GameActionManager.class,"damageReceivedThisTurn");
            return offset(LineFinder.findInOrder(ctMethodToPatch, new ArrayList(), finalMatcher),0);
        }

        private static int[] offset(int[] originalArr, int offset) {
            for(int i = 0; i < originalArr.length; ++i) {
                originalArr[i] += offset;
            }

            return originalArr;
        }
    }
    private static boolean Rendering = false;
    @SpirePatch(clz = EnergyPanel.class, method = "renderOrb", paramtypes = { "com.badlogic.gdx.graphics.g2d.SpriteBatch"})
    public static class RenderPatch{
        public static void Prefix(EnergyPanel __instance, SpriteBatch sb){
            if(getStigmataRender()){
                renderStressCounter(sb, __instance.current_x);
            }
        }
    }

    @SpirePatch(clz = EnergyPanel.class, method = "update")
    public static class UpdatePatch{
        public static void Prefix(EnergyPanel __instance){
            // Only update when rendering elements counter
            if (getStigmataRender()) {
                updateStigmataCounter();
            }
        }
    }
    @SpirePatch2(
            cls = "com.megacrit.cardcrawl.core.AbstractCreature",
            method = "<class>"
    )
    public static class StigmataField {
        public static SpireField<Integer> Stigmata = new SpireField(() -> 0);
    }
    // UI Class

    public static class StigmataUI extends ClickableUIElement {
        public Hitbox hb;
        private FrameBuffer fbo;
        private static float hb_w = 54.0F;
        private static float hb_h = 64.0F;
        private static final float baseX = 280.0F * Settings.scale;
        private static final float baseY = 120.0F * Settings.scale;
        private float x = baseX;
        private float y = baseY;
        private static int IMG_DIM = 256;
        private static int rotation;
        public static float fontScale = 1.0F;
        public static Texture Gem;
        private static final UIStrings StressTooltips = CardCrawlGame.languagePack.getUIString("dragonmod:StressUITooltips");
        public StigmataUI(Texture image){
            super(image, baseX, baseY , hb_w, hb_h);
            this.image = image;
            Gem = ImageMaster.loadImage("dragonmod/ui/StigmataUI/Stigmata.png");
            hb = new Hitbox(baseX + hb_w,baseY/2,hb_w,hb_h);
            this.fbo = new FrameBuffer(Pixmap.Format.RGBA8888, IMG_DIM, IMG_DIM, false, false);
            this.setClickable(false);
        }
        public void render(SpriteBatch sb, float current_x){
            rotation += 1;
            updateHitboxPosition(current_x + x - baseX, y);
            sb.setBlendFunction(770, 771);
            sb.draw(Gem, this.x - (float) image.getWidth(), this.y - (float) image.getHeight()/1.5f, (float) Gem.getWidth() / 2.0F, (float)Gem.getHeight() / 2.0F,
                    (float)Gem.getHeight(), (float) Gem.getHeight(), 2.0f * Settings.scale, 2.0f * Settings.scale, 0,0,0,54,64,false,false);
            FontHelper.renderFontCentered(sb, FontHelper.energyNumFontPurple, StigmataField.Stigmata.get(AbstractDungeon.player).toString(),  (baseX* Settings.scale)+25, (baseY * Settings.scale)+20, Color.WHITE, fontScale);
            hb.render(sb);
        }

        private void updateHitboxPosition(float x, float y){
            hb.translate(x - hb_w/2, y/2);
        }
        @Override
        protected void onHover() {
            TipHelper.renderGenericTip(x + 55.0F * Settings.scale, y + 60.0F * Settings.scale, StressTooltips.TEXT[0],
                    StressTooltips.TEXT[1]+StressTooltips.TEXT[2]);
        }

        @Override
        protected void onUnhover() {

        }

        @Override
        protected void onClick() {

        }
    }
}
