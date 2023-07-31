package dragonmod.cards.Justicar;


import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.characters.TheJusticar;

public class InfernoWard extends AbstractPrimalCard {


    public static final String ID = DragonMod.makeID(InfernoWard.class.getSimpleName());

    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    public static final AbstractCard.CardColor COLOR = TheJusticar.Enums.Justicar_Red_COLOR;

    private static final int COST = 4;
    private static final int BLOCK = 8;
    private static final int UPGRADE_PLUS_BLOCK = 3;


    // /STAT DECLARATION/


    public InfernoWard() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseBlock = BLOCK;
        baseDamage = baseBlock;
        baseMagicNumber = magicNumber = 5;
        exhaust = true;
        cardToPreview.add(new DayofReckoning());
        cardToPreview.add(new FinalHour());
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard card = new DayofReckoning();
        if (upgraded){
            card.upgrade();
        }
        addToBot(new MakeTempCardInDrawPileAction(card,1,true,false));
        super.use(p,m);
    }


    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(3);
            for (AbstractCard c : cardToPreview){
                c.upgrade();
            }
            initializeDescription();
        }
    }
}
