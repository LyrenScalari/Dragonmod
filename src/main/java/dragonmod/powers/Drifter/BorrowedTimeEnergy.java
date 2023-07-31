package dragonmod.powers.Drifter;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.actions.GainStressAction;
import dragonmod.powers.BasePower;

import static dragonmod.DragonMod.makeID;

public class BorrowedTimeEnergy extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("BorrowedEnergy");
    public BorrowedTimeEnergy(AbstractCreature owner, int amount) {
        super(POWER_ID,PowerType.DEBUFF,true,owner,owner, amount);
        this.loadRegion("time");
        updateDescription();
    }

    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, Color.RED.cpy());
    }
    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+amount+DESCRIPTIONS[1];
    }
    public void atEndOfTurn(boolean isPlayer) {
        addToBot(new GainStressAction(amount));
        addToBot(new RemoveSpecificPowerAction(owner,owner,this));
    }
    public void onEnergyGained(int e){
        addToTop(new ReducePowerAction(owner,owner,this,e));
        addToBot(new LoseEnergyAction(e));
    }
    public void atStartOfTurnPostDraw() {
        this.flash();
        if (amount > AbstractDungeon.player.energy.energy){
            addToTop(new ReducePowerAction(owner,owner,this, AbstractDungeon.player.energy.energy));
            addToBot(new LoseEnergyAction(AbstractDungeon.player.energy.energy));
        } else {
            addToTop(new ReducePowerAction(owner,owner,this, amount));
            addToBot(new LoseEnergyAction(amount));
        }
    }
    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new BorrowedTimeCards(owner, amount);
    }
}