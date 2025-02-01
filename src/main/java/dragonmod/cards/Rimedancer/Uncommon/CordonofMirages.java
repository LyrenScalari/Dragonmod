package dragonmod.cards.Rimedancer.Uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.util.EnchantmentsField;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.Wiz;

import static dragonmod.util.EnchantmentsManager.Cantrip;

public class CordonofMirages extends AbstractRimedancerCard {
    public static final String ID = CordonofMirages.class.getSimpleName();
    public CordonofMirages(){
        super(ID,1,CardType.SKILL,CardRarity.UNCOMMON,CardTarget.NONE);
        setSelfRetain(false,true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                CardGroup pool = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                for (AbstractCard card : EnchantmentsField.Enchantments.get(Wiz.Player()).group) {
                    if (card.hasTag(Cantrip) || card.hasTag(EnchantmentsManager.Sleeved)){
                        pool.addToBottom(card);
                    }
                }
                for (AbstractCard card : pool.group) {
                    EnchantmentsField.Enchantments.get(Wiz.Player()).group.remove(card);
                }
                for (AbstractCard card :  Wiz.Player().hand.group) {
                    Wiz.Player().hand.removeCard(card);
                    card.tags.add(EnchantmentsManager.Sleeved);
                    EnchantmentsManager.addCard(card,true,Wiz.Player());
                }
                for (AbstractCard card : pool.group) {
                    card.unfadeOut();
                    card.lighten(true);
                    card.resetAttributes();
                    Wiz.Player().hand.addToHand(card);
                }
            }
        });
    }
}
