package dragonmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import dragonmod.util.EnchantmentsField;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.Wiz;

import static dragonmod.util.EnchantmentsManager.Cantrip;
import static dragonmod.util.EnchantmentsManager.EmptyBagOfTricks;

public class FlourishAction extends AbstractGameAction {
    public FlourishAction() {

    }

    @Override
    public void update() {
        isDone = true;
        if (!EmptyBagOfTricks()){
            AbstractCard target = EnchantmentsManager.getSleevedCard();
            EnchantmentsField.Enchantments.get(Wiz.Player()).remove(target);
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
                            EnchantmentsManager.addCard(c,true,Wiz.Player());
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
                            EnchantmentsManager.addCard(c,true,Wiz.Player());
                        }
                    });
                }
            }
            for (AbstractCard c : Wiz.Player().exhaustPile.group){
                if (c.hasTag(Cantrip)){
                    Wiz.att(new AbstractGameAction() {
                        @Override
                        public void update() {
                            Wiz.Player().exhaustPile.removeCard(c);
                            EnchantmentsManager.addCard(c,true,Wiz.Player());
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
                            EnchantmentsManager.addCard(c,true,Wiz.Player());
                        }
                    });
                }
            }
            Wiz.atb(new FlourishAction());
            EnchantmentsManager.update();
        }
    }
}
