package dragonmod.orbs;

import basemod.abstracts.CustomOrb;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.combat.FrostOrbPassiveEffect;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;
import dragonmod.DragonMod;
import dragonmod.powers.Rimedancer.Chillpower;
import dragonmod.ui.ThrowIceDaggerEffect;
import dragonmod.util.OnUseCardOrb;
import dragonmod.util.TextureLoader;
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
    private boolean hFlip1 = MathUtils.randomBoolean();
    public static boolean chaining = false;
    public Icicle() {
        super(ORB_ID, orbString.NAME, PASSIVE_AMOUNT, EVOKE_AMOUNT, DESCRIPTIONS[1], DESCRIPTIONS[3], DragonMod.orbPath("Icicle.png"));
        img = TextureLoader.getTexture(DragonMod.orbPath("Icicle.png"));
        updateDescription();
        angle = MathUtils.random(360.0f); // More Animation-related Numbers
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
                            if (Icicle.target.currentBlock - evokeAmount > 0) {
                                Icicle.target.getPower(Chillpower.POWER_ID).flash();
                                Wiz.att(new ReducePowerAction(Icicle.target, Icicle.target, Icicle.target.getPower(Chillpower.POWER_ID), 1));
                                Wiz.block(Wiz.adp(), Icicle.target.getPower(Chillpower.POWER_ID).amount);
                                isDone = true;
                            }
                        }
                    }
                });
            }
            Wiz.dmgtop(target,info, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
            Wiz.att(new VFXAction(new ThrowIceDaggerEffect(target.hb.cX, target.hb.cY,-5)));
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

    @Override
    public void onEndOfTurn() {
        if (target != null){
            AbstractDungeon.actionManager.addToBottom(
                    new VFXAction(new OrbFlareEffect(this, OrbFlareEffect.OrbFlareColor.FROST), 0.1f));
            CardCrawlGame.sound.play("ORB_FROST_EVOKE", 0.1F);
            DamageInfo info = new DamageInfo(AbstractDungeon.player, this.passiveAmount, DamageInfo.DamageType.THORNS);
            info.output = AbstractOrb.applyLockOn(target, info.base);
            Wiz.atb(new VFXAction(new ThrowIceDaggerEffect(target.hb.cX, target.hb.cY,-5)));
            Wiz.dmg(target,info, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
            if (target.hasPower(Chillpower.POWER_ID)) {
                Wiz.atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        if (Icicle.target.hasPower(Chillpower.POWER_ID)) {
                            if (Icicle.target.currentBlock - passiveAmount < 0) {
                                Icicle.target.getPower(Chillpower.POWER_ID).flash();
                                Wiz.att(new ReducePowerAction(Icicle.target, Icicle.target, Icicle.target.getPower(Chillpower.POWER_ID), 1));
                                Wiz.block(Wiz.adp(), Icicle.target.getPower(Chillpower.POWER_ID).amount);
                            }
                        }
                        isDone = true;
                    }
                });
            }
        }
    }
    @Override
    public void playChannelSFX() {

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
        sb.setColor(new Color(1.0f, 1.0f, 1.0f, c.a / 2.0f));
        sb.draw(img, this.cX - 48.0F + this.bobEffect.y / 4.0F, this.cY - 48.0F - this.bobEffect.y / 4.0F, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, 0.0F, 0, 0, 96, 96, this.hFlip1, false);
        renderText(sb);
        hb.render(sb);
    }

    @Override
    public void onUseCardOrb(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK && action.target instanceof AbstractMonster){
            target = (AbstractMonster)action.target;
            updateDescription();
        } else {
            if (target.isDeadOrEscaped()){
                target = null;
            }
        }
    }
}
