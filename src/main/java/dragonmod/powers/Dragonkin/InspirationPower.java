package dragonmod.powers.Dragonkin;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.stance.CalmParticleEffect;
import dragonmod.DragonMod;
import dragonmod.powers.BaseTwoAmountPower;
import dragonmod.ui.HaloEffect;
import dragonmod.util.StigmataManager;
import dragonmod.util.Wiz;

//Recover HP equal to hp lost up to the amount at start of turn.

public class InspirationPower extends BaseTwoAmountPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = DragonMod.makeID("Inspiration");
    public static float particleTimer = 0.0f;

    public InspirationPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(POWER_ID,PowerType.BUFF,false,owner,source, amount);
        updateDescription();
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
    public void updateParticles() {
        amount2 = StigmataManager.StigmataField.Stigmata.get(Wiz.Player());
        if (!Settings.DISABLE_EFFECTS) {// 33
            particleTimer -= Gdx.graphics.getDeltaTime();// 35
            if (particleTimer < 0.0F) {// 36
                particleTimer = 0.04F;// 37
                AbstractDungeon.effectsQueue.add(new CalmParticleEffect());// 38
            }
        }
    }
    public void atStartOfTurnPostDraw() {
        flash();
        Wiz.vfx(new HaloEffect(Wiz.Player(), Color.GOLD,Color.GOLDENROD,""));
        if (amount2 > 0) {
            Wiz.atb(new HealAction(Wiz.Player(),Wiz.Player(),Math.min(amount2, amount)));
        }
        StigmataManager.spendStigmata(StigmataManager.getStigmataTotal());
    }
    @Override
    public AbstractPower makeCopy() {
        return new InspirationPower(owner, source, amount);
    }
    public float[] _lightsOutGetXYRI() {
        return new float[] {owner.hb.cX, owner.hb.cY,(float) (owner.hb.width+(0.35*amount)), 0.45f * amount};
    }
    public Color[] _lightsOutGetColor() {
        return new Color[] {Color.GOLD.cpy()};
    }
}
