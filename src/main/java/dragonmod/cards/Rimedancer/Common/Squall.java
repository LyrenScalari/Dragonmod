package dragonmod.cards.Rimedancer.Common;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LockOnPower;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.util.Wiz;

public class Squall extends AbstractRimedancerCard {
    public static final String ID = Squall.class.getSimpleName();

    public Squall() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        setBlock(8,4);
        setMagic(3,1);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.block(p,block);
        Wiz.applyToEnemy(m,new LockOnPower(m,magicNumber));
    }
}
