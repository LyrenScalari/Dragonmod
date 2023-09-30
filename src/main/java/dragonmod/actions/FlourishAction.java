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
            Wiz.adp().hand.addToHand(target);
        } else {
            for (AbstractCard c : Wiz.adp().drawPile.group){
                if (c.hasTag(Cantrip)){
                    Wiz.att(new AbstractGameAction() {
                        @Override
                        public void update() {
                            isDone = true;
                            Wiz.adp().drawPile.removeCard(c);
                            CantripPile.addToTop(c);
                        }
                    });
                }
            }
            for (AbstractCard c : Wiz.adp().discardPile.group){
                if (c.hasTag(Cantrip)){
                    Wiz.att(new AbstractGameAction() {
                        @Override
                        public void update() {
                            isDone = true;
                            Wiz.adp().discardPile.removeCard(c);
                            CantripPile.addToTop(c);
                        }
                    });
                }
            }
            for (AbstractCard c : Wiz.adp().exhaustPile.group){
                if (c.hasTag(Cantrip)){
                    Wiz.att(new AbstractGameAction() {
                        @Override
                        public void update() {
                            Wiz.adp().discardPile.removeCard(c);
                            CantripPile.addToTop(c);
                        }
                    });
                }
            }
            for (AbstractCard c : Wiz.adp().hand.group){
                if (c.hasTag(Cantrip)){
                    Wiz.att(new AbstractGameAction() {
                        @Override
                        public void update() {
                            isDone = true;
                            Wiz.adp().hand.removeCard(c);
                            CantripPile.addToTop(c);
                        }
                    });
                }
            }
            Wiz.atb(new FlourishAction());
        }
    }
}
