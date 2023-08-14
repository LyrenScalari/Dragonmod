package dragonmod.cards.Rimedancer.Uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.powers.Rimedancer.PrecisionPower;
import dragonmod.powers.Rimedancer.RimeShankPower;
import dragonmod.util.Wiz;

public class Rimeshank extends AbstractRimedancerCard {
    public static final String ID = Rimeshank.class.getSimpleName();
    public Rimeshank(){
        super(ID,2,CardType.POWER,CardRarity.UNCOMMON,CardTarget.SELF);
        setInnate(false,true);
        setMagic(2,2);
        setMagic2(4,2);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new PrecisionPower(p,magicNumber));
        Wiz.applyToSelf(new RimeShankPower(p,p,SecondMagicNumber));
    }
}
