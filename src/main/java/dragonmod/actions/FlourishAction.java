package dragonmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import dragonmod.util.Wiz;

import static dragonmod.util.CantripManager.Cantrip;
import static dragonmod.util.CantripManager.CantripPile;

public class FlourishAction extends AbstractGameAction {
    public FlourishAction() {

    }

    @Override
    public void update() {
        isDone = true;
        if (!CantripPile.isEmpty()){
            AbstractCard target = CantripPile.getRandomCard(true);
            CantripPile.removeCard(target);
            target.unfadeOut();
            target.lighten(true);
            target.resetAttributes();
            Wiz.Player().hand.addToHand(target);
        } else {
            for (AbstractCard c : Wiz.Player().drawPile.group){
                if (c.hasTag(Cantrip)){
                    Wiz.att(new AbstractGameAction() {
                        @Override
                        public void update() {
                            isDone = true;
                            Wiz.Player().drawPile.removeCard(c);
                            CantripPile.addToTop(c);
                        }
                    });
                }
            }
            for (AbstractCard c : Wiz.Player().discardPile.group){
                if (c.hasTag(Cantrip)){
                    Wiz.att(new AbstractGameAction() {
                        @Override
                        public void update() {
                            isDone = true;
                            Wiz.Player().discardPile.removeCard(c);
                            CantripPile.addToTop(c);
                        }
                    });
                }
            }
            for (AbstractCard c : Wiz.Player().exhaustPile.group){
                if (c.hasTag(Cantrip)){
                    Wiz.att(new AbstractGameAction() {
                        @Override
                        public void update() {
                            Wiz.Player().discardPile.removeCard(c);
                            CantripPile.addToTop(c);
                        }
                    });
                }
            }
            for (AbstractCard c : Wiz.Player().hand.group){
                if (c.hasTag(Cantrip)){
                    Wiz.att(new AbstractGameAction() {
                        @Override
                        public void update() {
                            isDone = true;
                            Wiz.Player().hand.removeCard(c);
                            CantripPile.addToTop(c);
                        }
                    });
                }
            }
            Wiz.atb(new FlourishAction());
        }
    }
}
