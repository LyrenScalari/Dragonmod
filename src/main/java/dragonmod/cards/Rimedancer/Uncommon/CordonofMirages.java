package dragonmod.cards.Rimedancer.Uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.actions.FlourishAction;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.util.Wiz;

public class CordonofMirages extends AbstractRimedancerCard {
    public static final String ID = CordonofMirages.class.getSimpleName();
    public CordonofMirages(){
        super(ID,0,CardType.SKILL,CardRarity.UNCOMMON,CardTarget.NONE);
        setMagic(2);
        cardsToPreview = new Dazed();
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.makeInHand(cardsToPreview.makeStatEquivalentCopy(),2);
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                AbstractCard glimpse = p.hand.getBottomCard();
                p.hand.removeCard(glimpse);
                p.hand.addToTop(glimpse);
                glimpse = p.hand.getBottomCard();
                p.hand.removeCard(glimpse);
                p.hand.addToTop(glimpse);
            }
        });
        Wiz.atb(new FlourishAction());
    }
}
