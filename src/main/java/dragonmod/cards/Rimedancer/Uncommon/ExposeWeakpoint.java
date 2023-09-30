package dragonmod.cards.Rimedancer.Uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.powers.Rimedancer.ExposeWeaknessPower;
import dragonmod.util.Wiz;

public class ExposeWeakpoint extends AbstractRimedancerCard {
    public static final String ID = ExposeWeakpoint.class.getSimpleName();
    public ExposeWeakpoint(){
        super(ID,2,CardType.POWER,CardRarity.UNCOMMON,CardTarget.ENEMY);
        setMagic(1,1);
        setMagic2(1);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToEnemy(m,new ExposeWeaknessPower(m,p,magicNumber));
    }
}