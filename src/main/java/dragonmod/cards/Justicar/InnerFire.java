package dragonmod.cards.Justicar;


import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.actions.CycleAction;
import dragonmod.powers.Dragonkin.SacrificePower;


public class InnerFire extends AbstractHolyCard {

    public static final String ID = DragonMod.makeID(InnerFire.class.getSimpleName());

    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;

    private static final int COST = 1;
    private static final int BLOCK = 12;
    private static final int UPGRADE_PLUS_BLOCK = 4;
    private static final int MAGIC = 3;
    private static final int UPGRADE_MAGIC = 1;

    public InnerFire() {
        super(ID,COST, TYPE, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;
        SecondMagicNumber = BaseSecondMagicNumber = 2;
        block = baseBlock = BLOCK;
    }

    // Actions the card should do.

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SelectCardsInHandAction(magicNumber,Manipstrings.EXTENDED_DESCRIPTION[1],false,false,(card)->true,(List)-> {
            addToBot(new DamageAction(p,new DamageInfo(p,2, DamageInfo.DamageType.THORNS)));
            for (AbstractCard c : List){
                if ((c instanceof AbstractPrimalCard || c.type == AbstractCard.CardType.STATUS || c.type == AbstractCard.CardType.CURSE) && !(c instanceof DivineEmber)){
                    addToBot(new CycleAction(c,0,new DivineEmber(c.makeStatEquivalentCopy())));
                } else {
                    addToBot(new CycleAction(c,0));
                    if (c instanceof AbstractHolyCard){
                        addToBot(new ApplyPowerAction(p,p,new SacrificePower(p,p,SecondMagicNumber)));
                    }
                }
            }
        }));
        super.use(p,m);
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber2(1);
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}