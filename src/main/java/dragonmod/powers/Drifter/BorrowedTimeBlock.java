package dragonmod.powers.Drifter;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.actions.GainStressAction;
import dragonmod.powers.BasePower;

import static dragonmod.DragonMod.makeID;

public class BorrowedTimeBlock extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("BorrowedTimeBlock");

    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.
    public BorrowedTimeBlock(AbstractCreature owner, int amount) {
        super(POWER_ID,PowerType.DEBUFF,true,owner,owner, amount);
        this.loadRegion("time");
        updateDescription();
    }

    public int onPlayerGainedBlock(int blockAmount) {
        if (blockAmount > amount){
            addToBot(new RemoveSpecificPowerAction(owner,owner,this));
            return blockAmount - amount;
        } else {
            addToBot(new ReducePowerAction(owner,owner,this,blockAmount));
            return 0;
        }

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

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new BorrowedTimeCards(owner, amount);
    }
}