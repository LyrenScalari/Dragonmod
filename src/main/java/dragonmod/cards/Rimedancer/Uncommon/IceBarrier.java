package dragonmod.cards.Rimedancer.Uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.powers.general.ReinforcePower;
import dragonmod.util.Wiz;

public class IceBarrier extends AbstractRimedancerCard {

    public static final String ID = IceBarrier.class.getSimpleName();
    public IceBarrier(){
        super(ID,2,CardType.SKILL,CardRarity.UNCOMMON,CardTarget.ALL);
        setBlock(12,3);
        setMagic(2,1);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.block(p,block);
        Wiz.applyToSelf(new ReinforcePower(p,p,magicNumber));
    }
}
