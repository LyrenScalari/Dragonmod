package dragonmod.cards.Justicar;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.powers.Dragonkin.Scorchpower;

public class Charr extends AbstractJusticarCard {
    public static final String ID =Charr.class.getSimpleName();
    public Charr() {
        super(ID, 1, CardType.SKILL,CardRarity.COMMON, CardTarget.ENEMY);
        setMagic(5,3);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m,p,new Scorchpower(m,p,magicNumber)));
    }
}