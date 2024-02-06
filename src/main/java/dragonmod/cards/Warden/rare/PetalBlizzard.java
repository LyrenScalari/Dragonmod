package dragonmod.cards.Warden.rare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Warden.AbstractWardenCard;

public class PetalBlizzard extends AbstractWardenCard {
    public static final String ID = PetalBlizzard.class.getSimpleName();

    public PetalBlizzard() {
        super(ID, 2, AbstractCard.CardType.ATTACK, CardRarity.RARE, AbstractCard.CardTarget.ENEMY);
        setDamage(4, 2);
        setMagic(4,1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }
}
