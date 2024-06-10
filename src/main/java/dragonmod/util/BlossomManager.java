package dragonmod.util;

import basemod.ClickableUIElement;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.PetalEffect;
import dragonmod.characters.TheWarden;

import java.util.ArrayList;

public class BlossomManager {
    public static BlossomUI blossomUI;
    public static boolean renderblossomui = false;

    public BlossomManager() {
    }
    public static void onBattleStart() {
        BlossomField.AmethystBlossom.set(AbstractDungeon.player,0);
        BlossomField.AmberBlossom.set(AbstractDungeon.player,0);
        BlossomField.EmeraldBlossom.set(AbstractDungeon.player,0);
    }
    public static void addAmberBlossom(int count) {
        BlossomField.AmberBlossom.set(Wiz.Player(),BlossomField.AmberBlossom.get(Wiz.Player())+count);
    }
    public static void addAmethystBlossom(int count) {
        BlossomField.AmethystBlossom.set(Wiz.Player(),BlossomField.AmethystBlossom.get(Wiz.Player())+count);
    }
    public static int getAmberBlossom() {
        return BlossomField.AmberBlossom.get(Wiz.Player());
    }
    public static int getAmethystBlossom() {
        return BlossomField.AmethystBlossom.get(Wiz.Player());
    }
    public static void addEmeraldBlossom(int count) {
        BlossomField.EmeraldBlossom.set(Wiz.Player(),BlossomField.EmeraldBlossom.get(Wiz.Player())+count);
        if (BlossomField.EmeraldBlossom.get(Wiz.Player()) >= 3){
            AbstractDungeon.effectsQueue.add(new PetalEffect());
            AbstractDungeon.effectsQueue.add(new PetalEffect());
            AbstractDungeon.effectsQueue.add(new PetalEffect());
            AbstractDungeon.effectsQueue.add(new PetalEffect());
            AbstractDungeon.effectsQueue.add(new PetalEffect());
            AbstractDungeon.effectsQueue.add(new PetalEffect());
            int stacksToRemove = BlossomField.EmeraldBlossom.get(Wiz.Player()) - (BlossomField.EmeraldBlossom.get(Wiz.Player())%3);
            Wiz.atb(new GainEnergyAction(stacksToRemove/3));
            BlossomField.EmeraldBlossom.set(Wiz.Player(),BlossomField.EmeraldBlossom.get(Wiz.Player())-stacksToRemove);
        }
    }
    // Render timing and rules
    public static boolean getBlossomRender() {
        if (CardCrawlGame.dungeon != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            if (renderblossomui) {
                if (blossomUI == null) {
                    blossomUI = new BlossomUI(ImageMaster.loadImage("dragonmod/ui/BlossomUI/Blossomvfx.png"));
                }
                return true;
            } else if (StigmataManager.StigmataField.Stigmata.get(AbstractDungeon.player) > 0 || AbstractDungeon.player.chosenClass == TheWarden.Enums.THE_WARDEN) {
                renderblossomui = true;
                if (blossomUI == null) {
                    blossomUI = new BlossomUI(ImageMaster.loadImage("dragonmod/ui/BlossomUI/Blossomvfx.png"));
                }
                return true;
            }
        }
        return false;
    }

    public static void renderBlossomCounters(SpriteBatch sb, float current_x) {
        blossomUI.render(sb, current_x);
    }

    public static void updateBlossomCounter() {
        blossomUI.update();
    }

    private static boolean Rendering = false;
    @SpirePatch(clz = EnergyPanel.class, method = "renderOrb", paramtypes = { "com.badlogic.gdx.graphics.g2d.SpriteBatch"})
    public static class RenderPatch{
        public static void Prefix(EnergyPanel __instance, SpriteBatch sb){
            if(getBlossomRender()){
                renderBlossomCounters(sb, __instance.current_x);
            }
        }
    }

    @SpirePatch(clz = EnergyPanel.class, method = "update")
    public static class UpdatePatch{
        public static void Prefix(EnergyPanel __instance){
            // Only update when rendering elements counter
            if (getBlossomRender()) {
                updateBlossomCounter();
            }
        }
    }
    @SpirePatch2(
            cls = "com.megacrit.cardcrawl.core.AbstractCreature",
            method = "<class>"
    )
    public static class BlossomField {
        public static SpireField<Integer> EmeraldBlossom = new SpireField(() -> 0);
        public static SpireField<Integer> AmberBlossom = new SpireField(() -> 0);
        public static SpireField<Integer> AmethystBlossom = new SpireField(() -> 0);
    }
    // UI Class

    public static class BlossomUI extends ClickableUIElement {
        public Hitbox hb;
        private FrameBuffer fbo;
        private static float hb_w = 200.0F;
        private static float hb_h = 200.0F;
        private static final float baseX = 280.0F * Settings.scale;
        private static final float baseY = 120.0F * Settings.scale;
        private float x = baseX;
        private float y = baseY;
        private final float AmberOffsetX = 120.0F * Settings.scale;
        private final float AmberOffsetY = 175.0F * Settings.scale;
        private float Amberx = AmberOffsetX;
        private float Ambery = AmberOffsetY;
        private final float AmethystOffsetX = 260.0F * Settings.scale;
        private final float AmethystOffsetY = 175.0F * Settings.scale;
        private float Amethystx = AmethystOffsetX;
        private float Amethysty = AmethystOffsetY;
        private final float EmeraldOffsetX = 185.0F * Settings.scale;
        private final float EmeraldOffsetY = 255.0F * Settings.scale;
        private float Emeraldx = EmeraldOffsetX;
        private float Emeraldy = EmeraldOffsetY;
        private static int IMG_DIM = 256;
        private static int rotation;
        public static float fontScale = 1.0F;
        public static Texture Amber;
        public static Texture Amethyst;
        public static Texture Emerald;
        private static final UIStrings AmberTooltips = CardCrawlGame.languagePack.getUIString("dragonmod:AmberBlossom");
        private static final UIStrings AmethystTooltips = CardCrawlGame.languagePack.getUIString("dragonmod:AmethystBlossom");
        private static final UIStrings EmeraldTooltips = CardCrawlGame.languagePack.getUIString("dragonmod:EmeraldBlossom");
        public BlossomUI(Texture image){
            super(image, baseX, baseY , hb_w, hb_h);
            this.image = image;
            hb = new Hitbox((100.0F * Settings.scale)+hb_w,(200.0F * Settings.scale)/2,hb_w,hb_h);
            Amber = ImageMaster.loadImage("dragonmod/ui/BlossomUI/AmberBlossom.png");
            Amethyst = ImageMaster.loadImage("dragonmod/ui/BlossomUI/AmethystBlossom.png");
            Emerald = ImageMaster.loadImage("dragonmod/ui/BlossomUI/EmeraldBlossom.png");
            this.fbo = new FrameBuffer(Pixmap.Format.RGBA8888, IMG_DIM, IMG_DIM, false, false);
            this.setClickable(false);
        }
        public void render(SpriteBatch sb, float current_x){
            rotation += 1;
            sb.setBlendFunction(770, 771);
            hb.translate(x - hb_w/2, y/2);
            hb.render(sb);
            //Render Amber Blossom UI
            sb.draw(Amber, (this.Amberx - (float) Amber.getWidth())+50, (this.Ambery - (float) Amber.getHeight()/1.5f)+50, (float) Amber.getWidth() / 2.0F, (float)Amber.getHeight() / 2.0F,
                    (float)Amber.getHeight(), (float) Amber.getHeight(), Settings.scale, Settings.scale, 0,0,0,84,84,false,false);

            FontHelper.renderFontCentered(sb, FontHelper.energyNumFontPurple, BlossomField.AmberBlossom.get(Wiz.Player()).toString(),  (AmberOffsetX* Settings.scale)+10, (AmberOffsetY * Settings.scale)+25, Color.WHITE, fontScale);

            //Again for Amethyst in a different location
            sb.draw(Amethyst, (this.Amethystx - (float) Amethyst.getWidth())+50, (this.Amethysty - (float) Amethyst.getHeight()/1.5f)+50, (float) Amethyst.getWidth() / 2.0F, (float)Amethyst.getHeight() / 2.0F,
                    (float)Amethyst.getHeight(), (float) Amethyst.getHeight(), Settings.scale, Settings.scale, 0,0,0,84,84,false,false);

            FontHelper.renderFontCentered(sb, FontHelper.energyNumFontPurple, BlossomField.AmethystBlossom.get(Wiz.Player()).toString(),  (AmethystOffsetX* Settings.scale)+10, (AmethystOffsetY * Settings.scale)+38, Color.WHITE, fontScale);

            //Third times the charm for Emerald
            sb.draw(Emerald, (this.Emeraldx - (float) Emerald.getWidth())+50, (this.Emeraldy - (float) Emerald.getHeight()/1.5f)+50, (float) Emerald.getWidth() / 2.0F, (float)Emerald.getHeight() / 2.0F,
                    (float)Emerald.getHeight(), (float) Emerald.getHeight(), Settings.scale, Settings.scale, 0,0,0,84,84,false,false);

            FontHelper.renderFontCentered(sb, FontHelper.energyNumFontPurple, BlossomField.EmeraldBlossom.get(Wiz.Player()).toString(),  (EmeraldOffsetX* Settings.scale)+10, (EmeraldOffsetY * Settings.scale)+20, Color.WHITE, fontScale);
        }
        @Override
        protected void onHover() {
            ArrayList<PowerTip> PowerTips = new ArrayList<>();
            PowerTips.add(new PowerTip(AmberTooltips.TEXT[0],AmberTooltips.TEXT[1]));
            PowerTips.add(new PowerTip(AmethystTooltips.TEXT[0],AmethystTooltips.TEXT[1]));
            PowerTips.add(new PowerTip(EmeraldTooltips.TEXT[0],EmeraldTooltips.TEXT[1]));
            TipHelper.queuePowerTips(x + 55.0F * Settings.scale, y + 60.0F * Settings.scale,PowerTips);
        }

        @Override
        protected void onUnhover() {

        }

        @Override
        protected void onClick() {

        }
    }
}