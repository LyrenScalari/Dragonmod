package dragonmod.cards.Rimedancer.Rare;

import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.powers.Rimedancer.powercards.ThousandsKnives;
import dragonmod.util.Wiz;

public class ThousandKnives extends AbstractRimedancerCard {
    public static final String ID = ThousandKnives.class.getSimpleName();
    public ThousandKnives() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        setMagic(1,1);
        cardsToPreview = new Shiv();
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new ThousandsKnives(p,magicNumber));
    }
}
