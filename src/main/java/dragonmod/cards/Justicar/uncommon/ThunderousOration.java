package dragonmod.cards.Justicar.uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.powers.Dragonkin.ThunderousOrationPower;
import dragonmod.util.Wiz;

public class ThunderousOration extends AbstractJusticarCard {

    public static final String ID = ThunderousOration.class.getSimpleName();
    public ThunderousOration(){
        super(ID,1,CardType.POWER,CardRarity.UNCOMMON,CardTarget.SELF);
        setMagic(2,1);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new ThunderousOrationPower(magicNumber));
    }
}

