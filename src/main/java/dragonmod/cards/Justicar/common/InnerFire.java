package dragonmod.cards.Justicar.common;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.powers.Dragonkin.Scorchpower;
import dragonmod.powers.general.PowerfulPower;
import dragonmod.util.Wiz;

public class InnerFire extends AbstractJusticarCard {

    public static final String ID = InnerFire.class.getSimpleName();
    public InnerFire(){
        super(ID,0,CardType.SKILL,CardRarity.COMMON,CardTarget.SELF);
        setMagic(4,6);
        setMagic2(2,1);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new PowerfulPower(p,p,SecondMagicNumber));
        Wiz.applyToSelf(new Scorchpower(p,p,magicNumber));
    }
}
