package dragonmod.powers.general;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DragonMod;
import dragonmod.powers.BasePower;
import dragonmod.util.Wiz;

public class ReinforcePower extends BasePower implements CloneablePowerInterface {
    public AbstractCreature source;
    public static final String POWER_ID = DragonMod.makeID("Reinforce");
    public ReinforcePower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(POWER_ID,PowerType.BUFF,false,owner,source, amount);
        updateDescription();
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    @Override
    public AbstractPower makeCopy() {
        return new ReinforcePower(owner, source, amount);
    }
    public void atEndOfRound() {
        if (this.amount == 0) {
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));// 37
        } else {
            this.addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));// 39
        }
    }
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type == DamageInfo.DamageType.NORMAL){
            if (owner.currentBlock < damageAmount){
                int unblockeddmg = owner.currentBlock - damageAmount;
                if (unblockeddmg >= amount/2){
                    Wiz.atb(new ReducePowerAction(owner,owner,this,amount));
                    return damageAmount-(amount/2);
                } else {
                    Wiz.atb(new ReducePowerAction(owner,owner,this,unblockeddmg*2));
                    return damageAmount-(unblockeddmg);
                }
            }
        }
        return damageAmount;
    }
    public void stackPower(int stackAmount) {
        if (stackAmount > amount){
            amount = stackAmount;
        }
    }
}
