package dragonmod.cards.Justicar.common;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.actions.CureAction;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.powers.Dragonkin.DivineConvictionpower;
import dragonmod.util.Wiz;

public class PulsingLight extends AbstractJusticarCard {

    public static final String ID = PulsingLight.class.getSimpleName();
    public PulsingLight(){
        super(ID,1,CardType.SKILL,CardRarity.COMMON,CardTarget.SELF);
        setCustomVar("H",4,2);
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
        Wiz.atb(new CureAction(customVar("H")));
    }
}
