package dragonmod.powers.Rimedancer;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DragonMod;
import dragonmod.orbs.Icicle;
import dragonmod.powers.BasePower;
import dragonmod.util.Wiz;

public class IcyVeinsPower extends BasePower implements CloneablePowerInterface, onRemoveOrbPower {
    public AbstractCreature source;
    public static final String POWER_ID = DragonMod.makeID("IcyVeins");

    public IcyVeinsPower(final int amount) {
        super(POWER_ID, AbstractPower.PowerType.BUFF, false, Wiz.Player(), Wiz.Player(), amount);
        updateDescription();
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new FrigidForm(amount);
    }

    @Override
    public void onRemoveOrb(AbstractOrb orb) {
        if (orb instanceof Icicle) {
            flash();
            Wiz.block(owner,amount);
        }
    }
}
