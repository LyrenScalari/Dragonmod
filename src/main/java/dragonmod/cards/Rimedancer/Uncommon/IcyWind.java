package dragonmod.cards.Rimedancer.Uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.powers.Rimedancer.Chillpower;
import dragonmod.powers.Rimedancer.SlowPower;
import dragonmod.util.Wiz;

public class IcyWind extends AbstractRimedancerCard {
    public static final String ID = IcyWind.class.getSimpleName();

    public IcyWind() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        setMagic(3,2);
        setMagic2(1,1);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToEnemy(m,new Chillpower(m,p,magicNumber));
        Wiz.applyToEnemy(m,new SlowPower(m,SecondMagicNumber));
    }
}
