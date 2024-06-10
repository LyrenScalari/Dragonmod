package dragonmod.powers.Rimedancer;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DragonMod;
import dragonmod.interfaces.OnChannelOrbPower;
import dragonmod.orbs.Icicle;
import dragonmod.orbs.Sleet;
import dragonmod.powers.BasePower;
import dragonmod.util.Wiz;

public class Subzero extends BasePower implements CloneablePowerInterface, OnChannelOrbPower {
    public AbstractCreature source;
    public static final String POWER_ID = DragonMod.makeID("Subzero");
    public Subzero(final int amount) {
        super(POWER_ID,PowerType.BUFF,false,Wiz.Player(),Wiz.Player(), amount);
        updateDescription();
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + (int)Math.ceil(amount/2f) + DESCRIPTIONS[1];
    }
    @Override
    public AbstractPower makeCopy() {
        return new Subzero(amount);
    }

    @Override
    public void onChannelOrb(AbstractOrb Orb) {
        if (Orb instanceof Icicle){
            ((Icicle) Orb).setBasePassiveAmount((int) Math.ceil(amount/2f));
            ((Icicle) Orb).setBaseEvokeAmount((int) Math.ceil(amount/2f));
            Wiz.atb(new ReducePowerAction(owner,owner,this,1));
        }
        if (Orb instanceof Sleet){
            ((Sleet) Orb).setBasePassiveAmount((int) Math.ceil(amount/2f));
            ((Sleet) Orb).setBaseEvokeAmount((int) Math.ceil(amount/2f));
            Wiz.atb(new ReducePowerAction(owner,owner,this,1));
        }
    }
    public float[] _lightsOutGetXYRI() {
        return new float[] {owner.hb.cX, owner.hb.cY, (float) (owner.hb.width+(0.01*(amount/2))), 0.05f *((float) amount /2)};
    }

    public Color[] _lightsOutGetColor() {
        return new Color[] {Color.NAVY.cpy()};
    }
}
