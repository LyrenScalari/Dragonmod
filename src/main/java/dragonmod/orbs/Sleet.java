package dragonmod.orbs;

import basemod.abstracts.CustomOrb;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;
import com.megacrit.cardcrawl.vfx.combat.FrostOrbPassiveEffect;
import com.megacrit.cardcrawl.vfx.combat.IceShatterEffect;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;
import dragonmod.DragonMod;
import dragonmod.powers.Rimedancer.Chillpower;
import dragonmod.util.TextureLoader;
import dragonmod.util.Wiz;


public class Sleet extends CustomOrb {
    public static final String ORB_ID = DragonMod.makeID("Sleet");
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static final int PASSIVE_AMOUNT = 2;
    private static final int EVOKE_AMOUNT = 3;
    // Animation Rendering Numbers - You can leave these at default, or play around with them and see what they change.
    private float vfxTimer = 1.0f;
    private float vfxIntervalMin = 0.1f;
    private float vfxIntervalMax = 0.4f;
    private static final float ORB_WAVY_DIST = 0.04f;
    private static final float PI_4 = 12.566371f;
    private boolean hFlip1 = MathUtils.randomBoolean();
    public Sleet() {
        super(ORB_ID, orbString.NAME, PASSIVE_AMOUNT, EVOKE_AMOUNT, DESCRIPTIONS[1], DESCRIPTIONS[3], DragonMod.orbPath("Sleet.png"));
        img = TextureLoader.getTexture(DragonMod.orbPath("Sleet.png"));
        updateDescription();
        angle = MathUtils.random(360.0f); // More Animation-related Numbers
        channelAnimTimer = 0.5f;
    }
    @Override
    public void onEvoke(){
        Wiz.atb(new VFXAction(new IceShatterEffect(hb.cX,hb.cY)));
        Wiz.atb(new VFXAction(new OrbFlareEffect(this, OrbFlareEffect.OrbFlareColor.FROST), 0.1f));
        Wiz.atb(new VFXAction(new BlizzardEffect(evokeAmount,false)));
        CardCrawlGame.sound.play("ORB_FROST_EVOKE", 0.1F);
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters){
            Wiz.applyToEnemy(m,new Chillpower(m,AbstractDungeon.player,evokeAmount));
        }
    }

    @Override
    public void updateDescription() { // Set the on-hover description of the orb
        applyFocus(); // Apply Focus (Look at the next method)
        description = DESCRIPTIONS[0] + passiveAmount + DESCRIPTIONS[1] + DESCRIPTIONS[2] + evokeAmount + DESCRIPTIONS[3];
    }
    @Override
    public AbstractOrb makeCopy() {
        return new Icicle();
    }

    @Override
    public void onEndOfTurn() {
        Wiz.atb(new VFXAction(new IceShatterEffect(hb.cX,hb.cY)));
        AbstractMonster m = AbstractDungeon.getRandomMonster();
        Wiz.atb(new VFXAction(new IceShatterEffect(m.hb.cX,m.hb.cY)));
        Wiz.applyToEnemy(m,new Chillpower(m,AbstractDungeon.player,passiveAmount));
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
}
