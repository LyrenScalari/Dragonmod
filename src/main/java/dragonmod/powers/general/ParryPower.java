package dragonmod.powers.general;

import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnLoseBlockPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DragonMod;
import dragonmod.powers.BasePower;
import dragonmod.util.Wiz;

public class ParryPower extends BasePower implements CloneablePowerInterface, OnLoseBlockPower {
    public AbstractCreature source;
    public static final String POWER_ID = DragonMod.makeID("Parry");
    public ParryPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(POWER_ID,PowerType.BUFF,false,owner,source, amount);
        updateDescription();
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    @Override
    public AbstractPower makeCopy() {
        return new ParryPower(owner, source, amount);
    }
    @Override
    public int onLoseBlock(DamageInfo damageInfo, int i) {
        if (damageInfo.type == DamageInfo.DamageType.NORMAL) {
            if (i <= owner.currentBlock) {
                if (i <= amount){
                    Wiz.atb(new ReducePowerAction(owner,owner,this,i));
                    Wiz.dmg(damageInfo.owner, new DamageInfo(owner, i, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
                } else {
                    Wiz.atb(new ReducePowerAction(owner,owner,this,amount));
                    Wiz.dmg(damageInfo.owner, new DamageInfo(owner, amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
                }
            } else {
                if (owner.currentBlock <= amount){
                    Wiz.atb(new ReducePowerAction(owner,owner,this,owner.currentBlock));
                    Wiz.dmg(damageInfo.owner, new DamageInfo(owner, owner.currentBlock, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
                } else {
                    Wiz.atb(new ReducePowerAction(owner,owner,this,amount));
                    Wiz.dmg(damageInfo.owner, new DamageInfo(owner, amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
                }

            }
        }
        return i;
    }
}
