package dragonmod.cards.Justicar.common;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.actions.CureAction;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.powers.Dragonkin.DivineConvictionpower;
import dragonmod.powers.general.CouragePower;
import dragonmod.util.Wiz;

public class InfuseCourage extends AbstractJusticarCard {

    public static final String ID = InfuseCourage.class.getSimpleName();
    public InfuseCourage(){
        super(ID,1,CardType.SKILL,CardRarity.COMMON,CardTarget.SELF);
        setCustomVar("H",6,2);
        setVarCalculation("H", (m, base) -> {
            AbstractPower p = null;
            if (AbstractDungeon.player != null){
                p = AbstractDungeon.player.getPower(DivineConvictionpower.POWER_ID);
            }
            if(p != null){
                return base + p.amount;
            } else return base;
        });
        setMagic(3,1);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new CureAction(customVar("H")));
        Wiz.applyToSelf(new CouragePower(p,p,magicNumber));
    }
}