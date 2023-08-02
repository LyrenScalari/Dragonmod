package dragonmod.cards.Justicar;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.actions.CycleAction;
import dragonmod.powers.Dragonkin.Scorchpower;


public class PowerThrough extends AbstractPrimalCard {

    public static final String ID = PowerThrough.class.getSimpleName();


    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;

    private static final int POTENCY= 10;
    private static final int UPGRADE_PLUS_DMG = 4;
    private static final int MAGIC = 6;
    private static final int UPGRADE_MAGIC = 2;
    public static int reductionval = 0;
    public static int baseCost = COST;

    public PowerThrough() {
        super(ID,  COST, TYPE, RARITY, TARGET);
        block = baseBlock = POTENCY;
        magicNumber = baseMagicNumber = 1;
        SecondMagicNumber =BaseSecondMagicNumber = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p,block));
        addToBot(new ApplyPowerAction(m,p,new Scorchpower(m,p,SecondMagicNumber)));
        addToBot(new SelectCardsInHandAction(magicNumber,Manipstrings.EXTENDED_DESCRIPTION[0],false,false,(card)->true,(List)-> {
            for (AbstractCard c : List){
                addToBot(new CycleAction(c,1));
            }
        }));
        super.use(p,m);
    }
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(3);
            upgradeMagicNumber2(1);
            initializeDescription();
        }
    }
}