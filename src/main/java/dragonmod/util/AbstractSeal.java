package dragonmod.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.BobEffect;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;
import dragonmod.DragonMod;
import dragonmod.actions.SmiteAction;
import dragonmod.powers.Dragonkin.WingsofLight;

public abstract class AbstractSeal extends AbstractNotOrb implements ReciveModifyDamageEffect {
    public static boolean DevotionEffects = false;
    public boolean DontBreak = true;
    public boolean reset = true;
    public AbstractSeal() {
        this.c = Settings.CREAM_COLOR.cpy();
        this.shineColor = new Color(1.0F, 1.0F, 1.0F, 0.0F);
        this.hb = new Hitbox(96.0F * Settings.scale, 96.0F * Settings.scale);
        this.img = ImageMaster.EYE_ANIM_0;
        this.bobEffect = new BobEffect(3.0F * Settings.scale, 3.0F);
        this.fontScale = 0.7F;
        this.showEvokeValue = true;
        this.channelAnimTimer = 0.5F;
        basePainAmount = PainAmount;
        baseBreakAmount = BreakAmount;
    }
    public void renderText(SpriteBatch sb) {
        FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.BreakAmount), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET - 4.0F * Settings.scale, new Color(0.2F, 1.0F, 1.0F, this.c.a), this.fontScale);
        FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.PainAmount), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET + 20.0F * Settings.scale,new Color(0.5F, 0.0F, 3.0F, this.c.a), this.fontScale);
    }
    @Override
    public int onReciveDamage(int damage, DamageInfo info) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && PainAmount > 0){
            if (!AbstractDungeon.actionManager.turnHasEnded){
                PainAmount -= damage;
            } else {
                if (!(info.owner instanceof AbstractMonster)){
                    PainAmount -= damage;
                }
            }
        }
        if (PainAmount < 0 && reset){
            PainAmount = 0;
        }
        if (PainAmount == 0){
            Break();
        }
        return damage;
    }
    public void onStartOfTurn() {
        if (PainAmount <= 0){
            PainAmount = basePainAmount;
        }
        DontBreak = true;
        reset = true;
    }
    public void Break(){
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new BorderFlashEffect(Color.GOLDENROD, true)));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new MiracleEffect()));
        if (DontBreak){
            PainAmount = basePainAmount;
        } else {
            PainAmount = -1;
            AbstractDungeon.actionManager.addToTop(new AbstractGameAction() {
                @Override
                public void update() {
                    DragonMod.Seals.remove(AbstractSeal.this);
                    isDone = true;
                }
            });
        }
        for (AbstractPower p : AbstractDungeon.player.powers){
            if (p instanceof WingsofLight){
                ((WingsofLight) p).onSealBreak();
            }
        }
    }
    public void onEndOfTurn() {
        if (!DevotionEffects){
            CardCrawlGame.sound.play("POWER_MANTRA", 0.05F);
            for (int i = 0; i < AbstractDungeon.miscRng.random(10, 15); ++i) {
                AbstractDungeon.effectsQueue.add(new DivineEyeParticle());
            }
            DevotionEffects = true;
        }
        if (!DragonMod.damagetaken){
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new BorderFlashEffect(Color.SCARLET, true)));
            if (this.PainAmount > 0){
                AbstractDungeon.actionManager.addToTop(new SmiteAction(AbstractDungeon.player,new DamageInfo(AbstractDungeon.player,this.PainAmount, DamageInfo.DamageType.THORNS)));
                DontBreak = false;
                reset = false;
            }
        }
    }
    public void updateAnimation() {
        this.bobEffect.update();
        if (this.channelAnimTimer != 0.0F) {
            this.channelAnimTimer -= Gdx.graphics.getDeltaTime();
            if (this.channelAnimTimer < 0.0F) {
                this.channelAnimTimer = 0.0F;
            }
        }
        this.c.a = Interpolation.pow2In.apply(1.0F, 0.01F, this.channelAnimTimer / 0.5F);
        this.scale = Interpolation.swingIn.apply(Settings.scale, 0.01F, this.channelAnimTimer / 0.5F);
        if (!AnimTimer){
            AbstractDungeon.effectsQueue.add(new SealParticleEffect(cX,cY,this));
            AnimTimer = true;
        }
    }
    public void updateDescription() {

    }
    public void update() {
        this.hb.update();
        angle = (360f/DragonMod.Seals.size()) * DragonMod.Seals.indexOf(this);
        cX = (AbstractDungeon.player.hb.cX-50f) + (float)(dy2*Math.cos((Math.toRadians(angle))));
        cY = (AbstractDungeon.player.hb.cY+50f) + (float)(dy2*Math.sin(Math.toRadians(angle)));
        hb.move(cX, cY); //I think this is correct, but might not be. Might need some offset calculations
        if (this.hb.hovered) {
            TipHelper.renderGenericTip(this.cX + 96.0F * Settings.scale, this.cY + 64.0F * Settings.scale, this.name, this.description);
        }
        this.fontScale = MathHelper.scaleLerpSnap(this.fontScale, 0.7F);
    }
    public float[] _lightsOutGetXYRI() {
        return new float[] {hb.cX, hb.cY, 150f, 5f};
    }

    public Color[] _lightsOutGetColor() {
        return new Color[] {Color.PURPLE.cpy()};
    }
}
