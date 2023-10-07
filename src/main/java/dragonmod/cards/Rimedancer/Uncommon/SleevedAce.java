package dragonmod.cards.Rimedancer.Uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;

public class SleevedAce extends AbstractRimedancerCard {
    public static final String ID = SleevedAce.class.getSimpleName();

    public SleevedAce() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        setMagic(2,1);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }
}
