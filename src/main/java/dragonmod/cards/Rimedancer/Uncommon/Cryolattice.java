package dragonmod.cards.Rimedancer.Uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.powers.Rimedancer.CryolatticePower;
import dragonmod.util.Wiz;

public class Cryolattice extends AbstractRimedancerCard {
    public static final String ID = Cryolattice.class.getSimpleName();
    public Cryolattice(){
        super(ID,1,CardType.POWER,CardRarity.UNCOMMON,CardTarget.SELF);
        setMagic(1,1);
}
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new CryolatticePower(p,p,magicNumber));
    }
}
