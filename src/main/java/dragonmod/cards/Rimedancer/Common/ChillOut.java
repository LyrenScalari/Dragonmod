package dragonmod.cards.Rimedancer.Common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.actions.FlourishAction;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.util.Wiz;

public class ChillOut extends AbstractRimedancerCard {

    public static final String ID = ChillOut.class.getSimpleName();

    public ChillOut() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        setMagic(3,-1);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new FlourishAction());
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                AbstractCard glimpse = p.hand.getTopCard();
                p.hand.removeCard(glimpse);
                p.hand.addToBottom(glimpse);
            }
        });
    }
}