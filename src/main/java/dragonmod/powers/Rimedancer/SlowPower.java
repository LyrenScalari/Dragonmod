package dragonmod.powers.Rimedancer;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DragonMod;
import dragonmod.interfaces.atLockonReceive;
import dragonmod.powers.BaseTwoAmountPower;
import dragonmod.util.Wiz;

public class SlowPower extends BaseTwoAmountPower implements CloneablePowerInterface, atLockonReceive {
    public AbstractCreature source;
    public static final String POWER_ID = DragonMod.makeID("Slow");
    public SlowPower(AbstractCreature owner, int Amount) {
        super(POWER_ID, AbstractPower.PowerType.DEBUFF,false,owner,Wiz.Player(), Amount);
        updateDescription();
    }
    public void atStartOfTurn() {
        this.addToBot(new ReducePowerAction(owner,owner,this,1));
        amount2 = 0;
    }
    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        updateDescription();
        return type == DamageInfo.DamageType.NORMAL ? damage * (1.0F + (float)this.amount2 * 0.1F) : damage;// 50 51
    }
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        updateDescription();
    }
    @Override
    public void updateDescription() {
        amount2 = AbstractDungeon.actionManager.cardsPlayedThisTurn.size();
        description = DESCRIPTIONS[0] + (10*amount2) + DESCRIPTIONS[1];
    }
    @Override
    public AbstractPower makeCopy() {
        return new SlowPower(owner,amount);
    }

    @Override
    public float applyLockOn(AbstractCreature target, int dmg) {
        return dmg * (1.0f+amount2*0.1f);
    }
}
