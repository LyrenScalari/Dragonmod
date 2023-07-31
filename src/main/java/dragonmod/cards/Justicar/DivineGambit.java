package dragonmod.cards.Justicar;


import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.evacipated.cardcrawl.mod.stslib.damagemods.BindingHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.actions.CycleAction;


public class DivineGambit extends AbstractPrimalCard {


    // TEXT DECLARATION

    public static final String ID = DragonMod.makeID(DivineGambit.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public DivineGambit(){
        super(ID,1,CardType.ATTACK,CardRarity.UNCOMMON,CardTarget.ALL);
        setDamage(4,2);
        setMagic(2,1);
        setMagic2(6);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(p,new DamageInfo(p,SecondMagicNumber, DamageInfo.DamageType.THORNS)));
        addToBot(new SelectCardsInHandAction(magicNumber,Manipstrings.EXTENDED_DESCRIPTION[0],false,false,(card)->true,(List)-> {
            for (AbstractCard c : List){
                addToBot(new CycleAction(c,1));
                AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng);
                addToBot(new DamageAction(randomMonster, BindingHelper.makeInfo(DivineGambit.this,p,damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
            }
        }));
        super.use(p,m);
    }
}