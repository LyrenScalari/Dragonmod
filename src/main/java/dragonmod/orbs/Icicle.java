package dragonmod.orbs;

import basemod.abstracts.CustomOrb;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.combat.FrostOrbPassiveEffect;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;
import dragonmod.DragonMod;
import dragonmod.actions.ThrowIcicleAction;
import dragonmod.powers.Rimedancer.Chillpower;
import dragonmod.interfaces.OnUseCardOrb;
import dragonmod.ui.TextureLoader;
import dragonmod.util.Wiz;

public class Icicle extends CustomOrb implements OnUseCardOrb {
    public static final String ORB_ID = DragonMod.makeID("Icicle");
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    public static AbstractMonster target;
    private static final int PASSIVE_AMOUNT = 2;
    private static final int EVOKE_AMOUNT = 4;
    // Animation Rendering Numbers - You can leave these at default, or play around with them and see what they change.
    private float vfxTimer = 1.0f;
    private float vfxIntervalMin = 0.1f;
    private float vfxIntervalMax = 0.4f;
    private static final float ORB_WAVY_DIST = 0.04f;
    private static final float PI_4 = 12.566371f;
    private boolean hFlip1 = false;
    public static boolean chaining = false;
    public boolean thrown = false;
    public float angle = 0.0f;
    public Icicle() {
        super(ORB_ID, orbString.NAME, PASSIVE_AMOUNT, EVOKE_AMOUNT, DESCRIPTIONS[1], DESCRIPTIONS[3], DragonMod.orbPath("Icicle.png"));
        img = TextureLoader.getTexture(DragonMod.orbPath("Icicle.png"));
        updateDescription();
        angle = MathUtils.random(360f); // More Animation-related Numbers
        channelAnimTimer = 0.5f;
    }
    public Icicle(int power) {
        super(ORB_ID, orbString.NAME, power, power+2, DESCRIPTIONS[1], DESCRIPTIONS[3], DragonMod.orbPath("Icicle.png"));
        img = TextureLoader.getTexture(DragonMod.orbPath("Icicle.png"));
        updateDescription();
        angle = MathUtils.random(360f); // More Animation-related Numbers
        channelAnimTimer = 0.5f;
    }
    @Override
    public void onEvoke(){
        Wiz.att(new VFXAction(new OrbFlareEffect(this, OrbFlareEffect.OrbFlareColor.FROST), 0.1f));
        Wiz.att(new VFXAction(new OrbFlareEffect(this, OrbFlareEffect.OrbFlareColor.FROST), 0.1f));
        CardCrawlGame.sound.play("ORB_FROST_EVOKE", 0.1F);
        if (target != null){
            DamageInfo info = new DamageInfo(AbstractDungeon.player, this.evokeAmount, DamageInfo.DamageType.THORNS);
            info.output = AbstractOrb.applyLockOn(target, info.base);
            if (target.hasPower(Chillpower.POWER_ID)){
                Wiz.atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        if (Icicle.target.hasPower(Chillpower.POWER_ID)) {
                            Icicle.target.getPower(Chillpower.POWER_ID).flash();
                            Wiz.att(new ReducePowerAction(Icicle.target, Icicle.target, Icicle.target.getPower(Chillpower.POWER_ID), 1));
                            Wiz.block(Wiz.Player(), Icicle.target.getPower(Chillpower.POWER_ID).amount);
                        }
                        isDone = true;
                    }
                });
            }
            Wiz.dmgtop(target,info, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
            Wiz.att(new ThrowIcicleAction(this,target.hb,Color.CYAN));
            if (!chaining){
                Wiz.atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        if (AbstractDungeon.player.orbs.get(0) instanceof Icicle){
                            Wiz.att(new EvokeOrbAction(1));
                        }
                        isDone = true;
                    }
                });
            }
        }
    }

    @Override
    public void updateDescription() { // Set the on-hover description of the orb
        applyFocus(); // Apply Focus (Look at the next method)
        if (target != null){
            description = DESCRIPTIONS[0] + passiveAmount + DESCRIPTIONS[1] + target.name + DESCRIPTIONS[3] + evokeAmount + DESCRIPTIONS[4] + target.name + DESCRIPTIONS[6];
        } else {
            description = DESCRIPTIONS[0] + passiveAmount + DESCRIPTIONS[1] + DESCRIPTIONS[2] + DESCRIPTIONS[3] + evokeAmount + DESCRIPTIONS[4] + DESCRIPTIONS[5] + DESCRIPTIONS[6];
        }
    }
    @Override
    public AbstractOrb makeCopy() {
        return new Icicle();
    }
    public void passiveEffect(AbstractMonster focus){
        DamageInfo info = new DamageInfo(AbstractDungeon.player, this.passiveAmount, DamageInfo.DamageType.THORNS);
        info.output = AbstractOrb.applyLockOn(focus, info.base);
        Wiz.atb(new ThrowIcicleAction(this,focus.hb,Color.CYAN));
        Wiz.dmg(focus,info, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
    }

    @Override
    public void onEndOfTurn() {
        if (target != null){
            AbstractDungeon.actionManager.addToBottom(
                    new VFXAction(new OrbFlareEffect(this, OrbFlareEffect.OrbFlareColor.FROST), 0.1f));
            CardCrawlGame.sound.play("ORB_FROST_EVOKE", 0.1F);
            DamageInfo info = new DamageInfo(AbstractDungeon.player, this.passiveAmount, DamageInfo.DamageType.THORNS);
            info.output = AbstractOrb.applyLockOn(target, info.base);
            Wiz.atb(new ThrowIcicleAction(this,target.hb,Color.CYAN));
            Wiz.dmg(target,info, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
            Wiz.atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        if (Icicle.target != null) {
                            if (Icicle.target.hasPower(Chillpower.POWER_ID)) {
                                Icicle.target.getPower(Chillpower.POWER_ID).flash();
                                Wiz.att(new ReducePowerAction(Icicle.target, Icicle.target, Icicle.target.getPower(Chillpower.POWER_ID), 1));
                                Wiz.block(Wiz.Player(), Icicle.target.getPower(Chillpower.POWER_ID).amount);
                            }
                        }
                        isDone = true;
                    }
            });
        }
    }
    @Override
    public void playChannelSFX() {

    }
    public int getBasePassiveAmount() {
        return basePassiveAmount;
    }
    public int getBaseEvokeAmount() {
        return baseEvokeAmount;
    }
    public void setBasePassiveAmount(int bonus) {
        basePassiveAmount += bonus;
        updateDescription();
    }
    public void setBaseEvokeAmount(int bonus) {
        baseEvokeAmount += bonus;
        updateDescription();
    }
    public float[] _lightsOutGetXYRI() {
        return new float[] {hb.x, hb.y, 75f, 0.75f};
    }

    public Color[] _lightsOutGetColor() {
        return new Color[] {Color.SKY.cpy()};
    }
    @Override
    public void updateAnimation() {// You can totally leave this as is.
        // If you want to create a whole new orb effect - take a look at conspire's WaterDamage Orb. It includes a custom sound, too!
        super.updateAnimation();
        angle += Gdx.graphics.getDeltaTime() * 45.0f;
        vfxTimer -= Gdx.graphics.getDeltaTime();
        if (vfxTimer < 0.0f) {
            AbstractDungeon.effectList.add(new FrostOrbPassiveEffect(cX, cY)); // This is the purple-sparkles in the orb. You can change this to whatever fits your orb.
            vfxTimer = MathUtils.random(vfxIntervalMin, vfxIntervalMax);
        }
    }
    @Override
    public void render(SpriteBatch sb) {
        Vector2 vector = null;
        if (target != null && target.isDeadOrEscaped()){
            target = null;
        }
        if (!thrown) {
            if (target != null){
                vector = new Vector2(target.hb.cX-hb.cX,target.hb.cY-hb.cY);
                float targetAngle = vector.angle();
                if (targetAngle - angle > 180) //Has to rotate 180+ degrees, meaning rotating other way is faster
                    targetAngle -= 360; //Rotate other way
                angle = MathHelper.angleLerpSnap(angle,targetAngle);
            } else {
                angle = MathHelper.angleLerpSnap(angle,100);
            }

            sb.setColor(new Color(1.0f, 1.0f, 1.0f, c.a / 2.0f));
            sb.draw(img, this.cX - 48.0F + this.bobEffect.y / 4.0F, this.cY - 48.0F - this.bobEffect.y / 4.0F, 48.0F, 48.0F, 142.0F, 96.0F, this.scale, this.scale, angle, 0, 0, 142, 96, this.hFlip1, false);
            renderText(sb);
            hb.render(sb);
        }
    }

    @Override
    public void onUseCardOrb(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK && action.target instanceof AbstractMonster){
            target = (AbstractMonster)action.target;
            updateDescription();
        } else {
            if (target != null && target.isDeadOrEscaped()){
                target = null;
            }
        }
    }
}
