package dragonmod.orbs;

import basemod.abstracts.CustomOrb;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.combat.FrostOrbPassiveEffect;
import com.megacrit.cardcrawl.vfx.combat.IceShatterEffect;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;
import dragonmod.DragonMod;
import dragonmod.powers.Rimedancer.Subzero;
import dragonmod.ui.TextureLoader;
import dragonmod.util.Wiz;

public class Snow extends CustomOrb{
    public static final String ORB_ID = DragonMod.makeID("Snow");
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    public static AbstractMonster target;
    private static final int PASSIVE_AMOUNT = 6;
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
    public Snow(int power) {
        super(ORB_ID, orbString.NAME, power, power, DESCRIPTIONS[1], DESCRIPTIONS[3], DragonMod.orbPath("Icicle.png"));
        img = TextureLoader.getTexture(DragonMod.orbPath("Icicle.png"));
        updateDescription();
        angle = MathUtils.random(360f); // More Animation-related Numbers
        channelAnimTimer = 0.5f;
        basePassiveAmount = PASSIVE_AMOUNT;
        baseEvokeAmount = EVOKE_AMOUNT;
    }
    @Override
    public void onEvoke(){
        Wiz.vfx(new OrbFlareEffect(this, OrbFlareEffect.OrbFlareColor.FROST), 0.1f);
        Wiz.applyToSelf(new Subzero(getBaseEvokeAmount()));
    }

    @Override
    public void updateDescription() { // Set the on-hover description of the orb
        applyFocus(); // Apply Focus (Look at the next method)
        description = DESCRIPTIONS[0] + passiveAmount + DESCRIPTIONS[1] + baseEvokeAmount + DESCRIPTIONS[2];
    }
    @Override
    public AbstractOrb makeCopy() {
        return new Icicle();
    }

    @Override
    public void onEndOfTurn() {
        Wiz.vfx(new OrbFlareEffect(this, OrbFlareEffect.OrbFlareColor.FROST), 0.1f);
        Wiz.vfx(new IceShatterEffect(hb.cX,hb.cY));
        Wiz.vfx(new IceShatterEffect(hb.cX,hb.cY));
        Wiz.vfx(new IceShatterEffect(hb.cX,hb.cY));
        Wiz.vfx(new IceShatterEffect(hb.cX,hb.cY));
        Wiz.block(Wiz.Player(),getBasePassiveAmount());
        setBasePassiveAmount(-1);
        if (getBasePassiveAmount() <= 0){
            Wiz.Player().orbs.remove(this);
            Wiz.Player().orbs.add(0, this);
            Wiz.atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    Wiz.Player().removeNextOrb();
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
}