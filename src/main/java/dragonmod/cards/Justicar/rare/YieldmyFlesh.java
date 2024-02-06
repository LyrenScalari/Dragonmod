package dragonmod.cards.Justicar.rare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.powers.Dragonkin.YieldMyFleshPower;
import dragonmod.util.Wiz;

public class YieldmyFlesh extends AbstractJusticarCard {

    public static final String ID = YieldmyFlesh.class.getSimpleName();
    public YieldmyFlesh(){
        super(ID,2,CardType.POWER,CardRarity.RARE,CardTarget.SELF);
        setMagic(3,2);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new YieldMyFleshPower(magicNumber));
    }
}
