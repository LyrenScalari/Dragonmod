package dragonmod.cards.Rimedancer.Common;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LockOnPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.util.Wiz;

public class MarkAssessment extends AbstractRimedancerCard {

    public static final String ID = MarkAssessment.class.getSimpleName();

    public MarkAssessment() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        setMagic(1,1);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToEnemy(m,new VulnerablePower(m,magicNumber,false));
        Wiz.applyToEnemy(m,new LockOnPower(m,magicNumber));
    }
}