package dragonmod.powers.Rimedancer;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DragonMod;
import dragonmod.powers.BasePower;
import dragonmod.util.Wiz;

public class BleedPower extends BasePower implements CloneablePowerInterface {
    private AbstractPower powerToLose;
    public static final String POWER_ID = DragonMod.makeID("Bleed");
    @Override
    public AbstractPower makeCopy() {
        return new BleedPower(owner,amount);
    }
    public BleedPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, Wiz.Player(),amount);
        updateDescription();
    }

    public void atStartOfTurn() {
        flash();
        Wiz.atb(new LoseHPAction(owner,owner,amount));
        addToBot(new RemoveSpecificPowerAction(owner, owner, this.ID));
    }

    public void onRemove() {
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
