package dragonmod.powers.Drifter;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.powers.BasePower;
import dragonmod.util.Wiz;

import static dragonmod.DragonMod.makeID;


public class BorrowedTimeCards extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("BorrowedCards");
    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.
    public BorrowedTimeCards(AbstractCreature owner, int amount) {
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
        addToBot(new RemoveSpecificPowerAction(owner,owner,this));
    }
    public void onCardDraw(AbstractCard card) {
        if (amount > 0){
            amount -= 1;
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    AbstractDungeon.player.hand.removeCard(card);
                    AbstractDungeon.player.drawPile.addToTop(card);
                    isDone =true;
                }
            });
            if (amount < 1){
                Wiz.atb(new RemoveSpecificPowerAction(owner,owner,this));
            }
        }

    }
    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new BorrowedTimeCards(owner, amount);
    }
}