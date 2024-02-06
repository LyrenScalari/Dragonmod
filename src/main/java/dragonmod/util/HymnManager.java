package dragonmod.util;

import basemod.ClickableUIElement;
import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.ExhaustPileViewScreen;
import com.megacrit.cardcrawl.ui.panels.DrawPilePanel;
import com.megacrit.cardcrawl.vfx.BobEffect;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import dragonmod.DragonMod;
import dragonmod.characters.TheJusticar;
import javassist.CtBehavior;

import java.util.ArrayList;

import static dragonmod.DragonMod.makeID;

public class HymnManager {
    public static VersePileButton versePileButton;
    public static ArrayList<AbstractNotOrb> ActiveVerses = new ArrayList<>();
    public static CardGroup VersePile;
    @SpireEnum
    public static AbstractCard.CardTags Verse;
    public static void renderCombatUiElements(SpriteBatch sb) {
        if (Wiz.isInCombat() && AbstractDungeon.player.chosenClass.equals(TheJusticar.Enums.THE_JUSTICAR)) {
            if (versePileButton != null) {
                versePileButton.setX(AbstractDungeon.overlayMenu.combatDeckPanel.current_x);
                versePileButton.render(sb);
            }
        }
    }
    public static void onBattleStart() {
        HymnManager.ActiveVerses.clear();
        HymnManager.VersePile.clear();
    }
    public static void addVerse(AbstractSeal Verse, AbstractCard source) {
        Verse.VerseSource = source;
        HymnManager.ActiveVerses.add(Verse);
    }
    public static int getDevotion() {
        return HymnManager.VersePile.size() + Wiz.Player().hand.size()-1;
    }
    public static class VersePileButton extends ClickableUIElement {
        private static final float X_OFF = 0f * Settings.scale;
        private static final float Y_OFF = 150f * Settings.scale;
        private static final float HB_WIDTH = 128f;
        private static final float HB_HEIGHT = 128f;
        private static final float COUNT_X = 48.0F * Settings.scale;
        private static final float COUNT_Y = -16.0F * Settings.scale;
        private static final float COUNT_OFFSET_X = 48.0F * Settings.scale;
        private static final float COUNT_OFFSET_Y = -18.0F * Settings.scale;
        private static final float DECK_TIP_X = 0F * Settings.scale;
        private static final float DECK_TIP_Y = 128.0F * Settings.scale;
        private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("HymnPile"));
        public static final String[] TEXT = uiStrings.TEXT;

        private final BobEffect bob;

        private boolean isOpen = false;

        private static Texture tex = DragonMod.DIVINE_ARMOR_ICON;

        public VersePileButton() {
            super((Texture) null,
                    0f,
                    Y_OFF,
                    HB_WIDTH,
                    HB_HEIGHT);
            bob = new BobEffect(1.1f);
        }

        @Override
        protected void onHover() {
        }

        @Override
        protected void onUnhover() {
        }

        @Override
        protected void onClick() {
            if (AbstractDungeon.player.chosenClass.equals(TheJusticar.Enums.THE_JUSTICAR)) {
                if (isOpen && AbstractDungeon.screen == AbstractDungeon.CurrentScreen.EXHAUST_VIEW) {
                    isOpen = false;
                    CardCrawlGame.sound.play("DECK_CLOSE");
                    AbstractDungeon.closeCurrentScreen();
                } else if (!AbstractDungeon.isScreenUp) {
                    if (VersePile.isEmpty()) {
                        AbstractPlayer p = AbstractDungeon.player;
                        AbstractDungeon.effectList.add(new ThoughtBubble(p.dialogX, p.dialogY, 3.0F, TEXT[2], true));
                    } else {
                        ExhaustPileViewScreenPatches.showCollection = true;
                        AbstractDungeon.exhaustPileViewScreen.open();
                        isOpen = true;
                    }
                }
            }
        }

        @Override
        public void setX(float x) {
            super.setX(x + X_OFF);
        }

        @Override
        public void update() {
            super.update();
            bob.update();
        }

        public float getRenderX() {
            return hitbox.x + hitbox.width / 2F;
        }

        public float getRenderY() {
            return hitbox.y + (hitbox.height / 2F) + bob.y;
        }

        @Override
        public void render(SpriteBatch sb) {
            if (!VersePile.isEmpty() && (AbstractDungeon.player.chosenClass == TheJusticar.Enums.THE_JUSTICAR)) {
                if (!AbstractDungeon.overlayMenu.combatDeckPanel.isHidden) {
                    float x = hitbox.x + hitbox.width / 2f;
                    float y = hitbox.y + hitbox.height / 2f;
                    sb.setColor(Color.WHITE);
                    draw(sb, tex, x, y + bob.y);

                    String msg = Integer.toString(VersePile.size());
                    sb.setColor(Color.WHITE);
                    draw(sb,
                            ImageMaster.DECK_COUNT_CIRCLE,
                            x + COUNT_OFFSET_X,
                            y + COUNT_OFFSET_Y);
                    FontHelper.renderFontCentered(sb, FontHelper.turnNumFont, msg, x + COUNT_X, y + COUNT_Y);

                    hitbox.render(sb);
                    if (hitbox.hovered && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.isScreenUp) {
                        TipHelper.renderGenericTip(x + DECK_TIP_X, y + DECK_TIP_Y, TEXT[0], TEXT[1]);
                    }
                }
            }
            this.hitbox.render(sb);
        }

        public Vector2 getLocation() {
            return new Vector2(x, y);
        }

        public static void draw(SpriteBatch sb, Texture texture, float cX, float cY) {
            drawScaledAndRotated(sb, texture, cX, cY, 1f, 0f);
        }

        public static void drawScaled(SpriteBatch sb, Texture texture, float cX, float cY, float scale) {
            drawScaledAndRotated(sb, texture, cX, cY, scale, 0f);
        }

        public static void drawRotated(SpriteBatch sb, Texture texture, float cX, float cY, float rotation) {
            drawScaledAndRotated(sb, texture, cX, cY, 1f, rotation);
        }

        public static void drawScaledAndRotated(SpriteBatch sb, Texture texture, float cX, float cY, float scale, float rotation) {
            float w = texture.getWidth();
            float h = texture.getHeight();
            float halfW = w / 2f;
            float halfH = h / 2f;
            sb.draw(texture,
                    cX - halfW,
                    cY - halfH,
                    halfW,
                    halfH,
                    w,
                    h,
                    scale * Settings.scale,
                    scale * Settings.scale,
                    rotation,
                    0,
                    0,
                    (int) w,
                    (int) h,
                    false,
                    false);
        }

    }
    public static class ExhaustPileViewScreenPatches {

        public static boolean showCollection = false;
        private static boolean showingCollection = false;
        private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("HymnPile"));

        @SpirePatch(
                clz = ExhaustPileViewScreen.class,
                method = "open"
        )
        public static class OpenExhaustPileViewScreenPatch {
            @SpireInsertPatch(locator = OpenExhaustPileViewScreenPatchLocator.class)
            public static void Insert(ExhaustPileViewScreen _instance) {
                if (showCollection) {
                    CardGroup group = ReflectionHacks.getPrivate(_instance, ExhaustPileViewScreen.class, "exhaustPileCopy");
                    group.clear();
                    group.group.addAll(VersePile.group);
                    for (AbstractCard c : group.group){
                        c.resetAttributes();
                        c.unhover();
                        c.stopGlowing();
                        c.setAngle(0.0F, true);
                        c.lighten(true);
                    }
                    showCollection = false;
                    showingCollection = true;
                } else {
                    showingCollection = false;
                }
            }
        }

        private static class OpenExhaustPileViewScreenPatchLocator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher matcher = new Matcher.MethodCallMatcher(ExhaustPileViewScreen.class, "hideCards");
                return LineFinder.findInOrder(ctBehavior, matcher);
            }
        }

        @SpirePatch(
                clz = ExhaustPileViewScreen.class,
                method = "render"
        )
        public static class RenderExhaustPileViewScreenPatch {
            @SpireInsertPatch(locator = RenderExhaustPileViewScreenPatchLocator.class)
            public static SpireReturn<Void> Insert(ExhaustPileViewScreen _instance, SpriteBatch sb) {
                if (showingCollection) {
                    FontHelper.renderDeckViewTip(sb, uiStrings.TEXT[0], 96.0F * Settings.scale, Settings.CREAM_COLOR);// 311
                    return SpireReturn.Return(null);
                } else {
                    return SpireReturn.Continue();
                }
            }
        }

        private static class RenderExhaustPileViewScreenPatchLocator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher matcher = new Matcher.MethodCallMatcher(FontHelper.class, "renderDeckViewTip");
                return LineFinder.findInOrder(ctBehavior, matcher);
            }
        }
    }
    @SpirePatch2(
            clz = UseCardAction.class,
            method = "update"
    )
    public static class SelfExilePatch {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static SpireReturn Insert(UseCardAction __instance) {
            AbstractCard targetcard = ReflectionHacks.getPrivate(__instance,UseCardAction.class,"targetCard");
            if (targetcard.hasTag(Verse)) {
                __instance.isDone = true;
                AbstractDungeon.player.cardInUse = null;
                CardCrawlGame.sound.play("POWER_MANTRA", 0.05F);
                if (AbstractDungeon.player.hoveredCard == targetcard) {
                    AbstractDungeon.player.releaseCard();
                }
                targetcard.shrink();
                AbstractDungeon.getCurrRoom().souls.empower(targetcard);
                AbstractDungeon.player.drawPile.removeCard(targetcard);
                VersePile.addToTop(targetcard);
                return SpireReturn.Return(null);
            } else {
                return SpireReturn.Continue();
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractCard.class, "freeToPlayOnce");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
    @SpirePatch(
            clz = DrawPilePanel.class,
            method = "render"
    )
    public static class RenderVerseButton {
        public static void Postfix(DrawPilePanel __instance, SpriteBatch spriteBatch) {
            HymnManager.renderCombatUiElements(spriteBatch);
        }
    }
    private static class Locator extends SpireInsertLocator {
        private Locator() {
        }

        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "decrementBlock");
            return offset(LineFinder.findInOrder(ctMethodToPatch, new ArrayList(), finalMatcher),0);
        }

        private static int[] offset(int[] originalArr, int offset) {
            for(int i = 0; i < originalArr.length; ++i) {
                originalArr[i] += offset;
            }

            return originalArr;
        }
    }
    @SpirePatch(
            clz = AbstractRoom.class,
            method = "endBattle"
    )
    public static class BattleEnd {
        public BattleEnd() {
        }

        public static void Prefix(AbstractRoom __instance) {
            HymnManager.ActiveVerses.clear();
        }
    }
}
