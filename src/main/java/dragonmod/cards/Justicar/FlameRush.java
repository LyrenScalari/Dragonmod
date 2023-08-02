package dragonmod.cards.Justicar;


import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.actions.CycleAction;


public class FlameRush extends AbstractPrimalCard {

    public static final String ID = FlameRush.class.getSimpleName();

    public FlameRush() {
        super(ID,1,CardType.ATTACK,CardRarity.COMMON,CardTarget.ENEMY);
        setDamage(6,1);
        setMagic(1,1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m,new DamageInfo(p,damage,DamageInfo.DamageType.NORMAL)));
        addToBot(new SelectCardsInHandAction(magicNumber,Manipstrings.EXTENDED_DESCRIPTION[0],false,false,(card)->true,(List)-> {
                for (AbstractCard c : List){
            addToBot(new CycleAction(c,1));
        }
        }));
        super.use(p,m);
    }
}