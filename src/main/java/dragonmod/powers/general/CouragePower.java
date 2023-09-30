package dragonmod.powers.general;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DragonMod;
import dragonmod.powers.BasePower;
import dragonmod.util.Wiz;

public class CouragePower extends BasePower implements CloneablePowerInterface {
    public AbstractCreature source;
    public static final String POWER_ID = DragonMod.makeID("Courage");
    public CouragePower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(POWER_ID,PowerType.BUFF,false,owner,source, amount);
        updateDescription();
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    @Override
    public AbstractPower makeCopy() {
        return new CouragePower(owner, source, amount);
    }
    public void atStartOfTurnPostDraw() {
        Wiz.atb(new DrawCardAction(1));
        Wiz.atb(new GainEnergyAction(1));
        this.addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));// 39
    }
    public void stackPower(int stackAmount) {
        if (stackAmount > amount){
            amount = stackAmount;
        }
    }

}
