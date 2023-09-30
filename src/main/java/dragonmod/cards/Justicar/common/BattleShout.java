package dragonmod.cards.Justicar.common;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.powers.general.PowerfulPower;
import dragonmod.util.Wiz;

public class BattleShout extends AbstractJusticarCard {

    public static final String ID = BattleShout.class.getSimpleName();
    public BattleShout(){
        super(ID,1,CardType.SKILL,CardRarity.COMMON,CardTarget.SELF);
        setMagic(1,1);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DrawCardAction(1));
        Wiz.applyToSelf(new PowerfulPower(p,p,magicNumber));
    }
}