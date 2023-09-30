package dragonmod.cards.Justicar.common;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.powers.general.ReinforcePower;
import dragonmod.util.Wiz;

public class ShieldsUp extends AbstractJusticarCard {

    public static final String ID = ShieldsUp.class.getSimpleName();
    public ShieldsUp(){
        super(ID,1,CardType.SKILL,CardRarity.COMMON,CardTarget.SELF);
        setBlock(8,4);
        setMagic(1);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.block(p,block);
        Wiz.applyToSelf(new ReinforcePower(p,p,magicNumber));
    }
}
