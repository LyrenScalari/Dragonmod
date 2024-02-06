package dragonmod.cards.Rimedancer.Uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;

public class Slipstream extends AbstractRimedancerCard {

    public static final String ID = Slipstream.class.getSimpleName();
    public Slipstream(){
        super(ID,0,CardType.SKILL,CardRarity.UNCOMMON,CardTarget.ENEMY);
        setBlock(4,2);
        setMagic(4,2);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }
}
