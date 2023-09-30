package dragonmod.cards.Justicar.starter;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.actions.CureAction;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.powers.Dragonkin.DivineConvictionpower;
import dragonmod.powers.Dragonkin.Scorchpower;
import dragonmod.util.Wiz;

public class Cauterize extends AbstractJusticarCard {
    public static final String ID = Cauterize.class.getSimpleName();
    public Cauterize(){
        super(ID,0,CardType.SKILL,CardRarity.BASIC,CardTarget.SELF);
        setCustomVar("H",5,3);
        setMagic(3,-1);
        setVarCalculation("H", (m, base) -> {
            AbstractPower p = null;
            if (AbstractDungeon.player != null){
                p = AbstractDungeon.player.getPower(DivineConvictionpower.POWER_ID);
            }
            if(p != null){
                return base + p.amount;
            } else return base;
        });
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new CureAction(customVar("H")));
        Wiz.applyToSelf(new Scorchpower(p,p,magicNumber));
    }
}
