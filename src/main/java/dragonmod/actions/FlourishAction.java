package dragonmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import dragonmod.powers.Rimedancer.powercards.ThousandsKnives;
import dragonmod.util.EnchantmentsField;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.Wiz;

import static dragonmod.util.EnchantmentsManager.*;

public class FlourishAction extends AbstractGameAction {
    public FlourishAction() {

    }

    @Override
    public void update() {
        isDone = true;
        if (!EmptyBagOfTricks()){
            AbstractCard target = EnchantmentsManager.getSleevedCard();
            EnchantmentsField.Enchantments.get(Wiz.Player()).group.remove(target);
            target.unfadeOut();
            target.lighten(true);
            target.resetAttributes();
            Wiz.Player().hand.addToHand(target);
            if (Wiz.Player().hasPower(ThousandsKnives.POWER_ID)){
                Wiz.Player().getPower(ThousandsKnives.POWER_ID).onSpecificTrigger();
            }
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
                            isDone = true;
                            Wiz.Player().exhaustPile.removeCard(c);
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
