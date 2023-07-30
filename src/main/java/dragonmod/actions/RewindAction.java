package dragonmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import theDragonkin.util.Wiz;

public class RewindAction extends AbstractGameAction {
    private AbstractCard targetCard;
    private int drawamt;
    private CardGroup location;
    public RewindAction(AbstractCard c, CardGroup origin) {
        targetCard = c;
        location = origin;
    }
    public RewindAction(AbstractCard c) {
        targetCard = c;
    }
    @Override
    public void update() {
        Wiz.applyToSelf(new DrawCardNextTurnPower(AbstractDungeon.player,1));
        AbstractDungeon.actionManager.addToTop(new AbstractGameAction() {
            @Override
            public void update() {
                if (location != null){
                    location.removeCard(targetCard);
                }
                AbstractDungeon.player.drawPile.addToTop(targetCard);
                isDone = true;
            }
        });
        isDone = true;
    }
}

