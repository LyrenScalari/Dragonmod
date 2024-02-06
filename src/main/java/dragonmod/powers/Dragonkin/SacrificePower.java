package dragonmod.powers.Dragonkin;

import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DragonMod;
import dragonmod.powers.BaseTwoAmountPower;

public class SacrificePower extends BaseTwoAmountPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = DragonMod.makeID("Sacrifice");
    public SacrificePower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(POWER_ID,NeutralPowertypePatch.NEUTRAL,false,owner,source, amount);
        amount2 = 5;
        updateDescription();
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount2 + DESCRIPTIONS[1];
    }
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        reducethis();
    }
    @Override
    public void onInitialApplication() {
        updateDescription();
        reducethis();
    }
    public void reducethis (){
        if (this.amount >= amount2){
            int stacksToRemove = amount - (amount%amount2);
            addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,  new DivineConvictionpower(AbstractDungeon.player, AbstractDungeon.player, stacksToRemove/amount2)));
            addToBot(new ReducePowerAction(owner, owner, this, stacksToRemove));
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new DivineConvictionpower(owner, source, amount);
    }
}