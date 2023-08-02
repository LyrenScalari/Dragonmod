package dragonmod.cards.Justicar;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import dragonmod.util.Wiz;


public class EduranceStrike extends AbstractJusticarCard {

    public static final String ID = EduranceStrike.class.getSimpleName();

    public EduranceStrike() {
        super(ID,1,CardType.SKILL,CardRarity.COMMON,CardTarget.SELF);
        setMagic(4,1);
        setMagic2(10,2);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(p,new DamageInfo(p,magicNumber)));
        Wiz.applyToSelf(new DrawCardNextTurnPower(p,1));
        Wiz.applyToSelfNextTurn(new VigorPower(p,SecondMagicNumber));
    }
}
