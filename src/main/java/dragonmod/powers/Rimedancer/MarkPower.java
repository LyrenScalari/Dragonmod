package dragonmod.powers.Rimedancer;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DragonMod;
import dragonmod.interfaces.atLockonFinalReceive;
import dragonmod.powers.BasePower;

public class MarkPower extends BasePower implements CloneablePowerInterface, atLockonFinalReceive {
    public AbstractCreature source;
    public static final String POWER_ID = DragonMod.makeID("Mark");
    public MarkPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(POWER_ID,PowerType.DEBUFF,false,owner,source, amount);
        updateDescription();
    }
    public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
        return damage + amount;
    }
    @Override
    public void atEndOfTurn(boolean isPlayer) {
        flash();
        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    @Override
    public AbstractPower makeCopy() {
        return new MarkPower(owner, source, amount);
    }

    @Override
    public float applyLockOnFinal(AbstractCreature target, int dmg) {
        return dmg + amount;
    }
}
