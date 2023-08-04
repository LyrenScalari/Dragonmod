package dragonmod.cards.Rimedancer.Uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;

public class Rimeshank extends AbstractRimedancerCard {
    public static final String ID = Rimeshank.class.getSimpleName();
    public Rimeshank(){
        super(ID,2,CardType.POWER,CardRarity.UNCOMMON,CardTarget.SELF);
        setInnate(false,true);

    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }
}
