package dragonmod.powers.Rimedancer;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DragonMod;
import dragonmod.orbs.Icicle;
import dragonmod.powers.BasePower;
import dragonmod.util.Wiz;

public class FrigidForm extends BasePower implements CloneablePowerInterface, onRemoveOrbPower {
    public AbstractCreature source;
    public static final String POWER_ID = DragonMod.makeID("FrigidForm");
    public FrigidForm(final int amount) {
        super(POWER_ID,PowerType.BUFF,false,Wiz.Player(),Wiz.Player(), amount);
        updateDescription();
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + Math.ceil(amount/2f) + DESCRIPTIONS[1];
    }
    @Override
    public AbstractPower makeCopy() {
        return new FrigidForm(amount);
    }

    @Override
    public void onRemoveOrb(AbstractOrb orb) {
        if (orb instanceof Icicle){
            flash();
            Wiz.applyToSelf(new Subzero(amount));
        }
    }
}
