package dragonmod.cards.Warden;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.actions.ScryCallbackAction;
import dragonmod.patches.TemporalStressField;
import dragonmod.util.Wiz;

public class TimeTurner extends AbstractWardenCard {


    // TEXT DECLARATION

    public static final String ID = DragonMod.makeID(TimeTurner.class.getSimpleName());

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.SPECIAL;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;

    private static final int COST = 1;
    private static final int BLOCK = 5;
    private static final int UPGRADE_PLUS_BLOCK = 3;


    // /STAT DECLARATION/


    public TimeTurner() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseBlock = BLOCK;
        exhaust = true;
        selfRetain = true;
        BaseSecondMagicNumber = SecondMagicNumber = 5;
        magicNumber = baseMagicNumber = 1;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
       addToBot(new ScryCallbackAction(SecondMagicNumber, cards-> {
           TemporalStressField.Stress.set(Wiz.adp(),TemporalStressField.Stress.get(Wiz.adp())+(cards.size()*magicNumber));
       }));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}
