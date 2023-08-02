package dragonmod.cards.Warden;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.SCVTemporalCardMod;
import dragonmod.actions.ScryCallbackAction;
import dragonmod.util.TypeEnergyHelper;


public class Gaze extends AbstractWardenCard {


    // TEXT DECLARATION

    public static final String ID = (Gaze.class.getSimpleName());
    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    private static final int COST = 1;
    private static final int BLOCK = 5;
    private static final int UPGRADE_PLUS_BLOCK = 3;


    // /STAT DECLARATION/


    public Gaze() {
        super(ID, COST, TYPE, RARITY, TARGET);
        magicNumber = baseMagicNumber = 4;
        BaseSecondMagicNumber = SecondMagicNumber = 4;
        energyCosts.put(TypeEnergyHelper.Mana.Temporal,SecondMagicNumber);
        CardModifierManager.addModifier(this,new SCVTemporalCardMod());
    }
    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ScryCallbackAction(magicNumber, cards-> {

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
