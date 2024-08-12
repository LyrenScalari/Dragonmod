package dragonmod.powers.Dragonkin;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DragonMod;
import dragonmod.powers.BasePower;

//Gain 1 dex for the turn for each card played.

public class InspirationPower extends BasePower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = DragonMod.makeID("Inspiration");

    public InspirationPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(POWER_ID,PowerType.BUFF,false,owner,source, amount);
        updateDescription();
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount;
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
