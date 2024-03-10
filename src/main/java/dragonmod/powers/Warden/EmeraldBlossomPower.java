package dragonmod.powers.Warden;

import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.PetalEffect;
import dragonmod.DragonMod;
import dragonmod.powers.BasePower;
import dragonmod.util.Wiz;

public class EmeraldBlossomPower extends BasePower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = DragonMod.makeID("EmeraldBlossom");
    private AbstractMonster Target;
    public EmeraldBlossomPower(int Amount) {
        super(POWER_ID, NeutralPowertypePatch.NEUTRAL,false,Wiz.Player(),Wiz.Player(), Amount);
        updateDescription();
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
    @Override
    public AbstractPower makeCopy() {
        return new EmeraldBlossomPower(amount);
    }
    public void stackPower(int stackAmount) {
        amount += stackAmount;
        this.flashWithoutSound();
        if (amount >= 3){
            AbstractDungeon.effectsQueue.add(new PetalEffect());
            AbstractDungeon.effectsQueue.add(new PetalEffect());
            AbstractDungeon.effectsQueue.add(new PetalEffect());
            AbstractDungeon.effectsQueue.add(new PetalEffect());
            AbstractDungeon.effectsQueue.add(new PetalEffect());
            AbstractDungeon.effectsQueue.add(new PetalEffect());
            int stacksToRemove = amount - (amount%3);
            addToBot(new GainEnergyAction(stacksToRemove/3));
            addToBot(new ReducePowerAction(owner, owner, this, stacksToRemove));
        }
    }
}