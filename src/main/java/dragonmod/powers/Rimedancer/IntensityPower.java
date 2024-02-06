package dragonmod.powers.Rimedancer;

import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import dragonmod.DragonMod;
import dragonmod.powers.BaseTwoAmountPower;
import dragonmod.util.Wiz;

public class IntensityPower extends BaseTwoAmountPower {
    private AbstractPower powerToLose;
    public static final String POWER_ID = DragonMod.makeID("Intensity");
    public IntensityPower(int amount,int Amount2) {
        super(POWER_ID, NeutralPowertypePatch.NEUTRAL, false, Wiz.Player(),Wiz.Player(), amount);
        amount2 = Amount2;
        updateDescription();
    }
    public void atStartOfTurnPostDraw() {
        flash();
        amount2 -= 1;
        if (amount2 <= 0){
            Wiz.atb(new RemoveSpecificPowerAction(owner,owner,this));
            Wiz.atb(new ReducePowerAction(owner,owner, owner.getPower(FocusPower.POWER_ID),amount));
        }
    }
}