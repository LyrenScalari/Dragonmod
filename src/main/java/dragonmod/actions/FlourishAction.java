package dragonmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dragonmod.powers.Rimedancer.powercards.ThousandsKnives;
import dragonmod.util.EnchantmentsField;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.Wiz;

import static dragonmod.util.EnchantmentsManager.Cantrip;
import static dragonmod.util.EnchantmentsManager.EmptyBagOfTricks;

public class FlourishAction extends AbstractGameAction {
    boolean reshuffled = false;
    public FlourishAction() {

    }

    @Override
    public void update() {
        isDone = true;
        reshuffled = false;
        if (!EmptyBagOfTricks()){
            AbstractCard target = EnchantmentsManager.getSleevedCard();
            EnchantmentsField.Enchantments.get(Wiz.Player()).group.remove(target);
            target.unfadeOut();
            target.lighten(true);
            target.resetAttributes();
            Wiz.Player().hand.addToHand(target);
            EnchantmentsManager.update();
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
                            AbstractDungeon.getCurrRoom().souls.empower(c);
                            EnchantmentsManager.addCard(c,true,Wiz.Player());
                        }
                    });
                    reshuffled = true;
                }
            }
            for (AbstractCard c : Wiz.Player().discardPile.group){
                if (c.hasTag(Cantrip)){
                    Wiz.att(new AbstractGameAction() {
                        @Override
                        public void update() {
                            isDone = true;
                            Wiz.Player().discardPile.removeCard(c);
                            AbstractDungeon.getCurrRoom().souls.empower(c);
                            EnchantmentsManager.addCard(c,true,Wiz.Player());
                        }
                    });
                    reshuffled = true;
                }
            }
            for (AbstractCard c : Wiz.Player().exhaustPile.group){
                if (c.hasTag(Cantrip)){
                    Wiz.att(new AbstractGameAction() {
                        @Override
                        public void update() {
                            isDone = true;
                            Wiz.Player().exhaustPile.removeCard(c);
                            c.unfadeOut();
                            c.lighten(true);
                            c.fadingOut = false;
                            AbstractDungeon.getCurrRoom().souls.empower(c);
                            EnchantmentsManager.addCard(c,true,Wiz.Player());
                        }
                    });
                    reshuffled = true;
                }
            }
            if (reshuffled){
                Wiz.atb(new FlourishAction());
                EnchantmentsManager.update();
            }
        }
    }
}
