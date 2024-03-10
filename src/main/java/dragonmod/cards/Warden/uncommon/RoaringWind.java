package dragonmod.cards.Warden.uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Warden.AbstractWardenCard;

public class RoaringWind extends AbstractWardenCard {
    public static final String ID = RoaringWind.class.getSimpleName();
    public RoaringWind(){
        super(ID,1,CardType.POWER,CardRarity.UNCOMMON,CardTarget.ENEMY);
        setMagic(2,1);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }
}
