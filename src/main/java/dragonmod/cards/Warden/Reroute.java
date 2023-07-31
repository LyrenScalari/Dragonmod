package dragonmod.cards.Warden;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.SCVTemporalCardMod;
import dragonmod.DragonMod;
import dragonmod.actions.RestoreAction;
import dragonmod.powers.Drifter.RiposteCripple;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;


public class Reroute extends AbstractWardenCard {


    // TEXT DECLARATION

    public static final String ID = DragonMod.makeID(Reroute.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;  //   since they don't change much.
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;       //
    private static final int COST = 1;  // COST = 1
    private static final int UPGRADED_COST = 1; // UPGRADED_COST = 1

    private static final int DAMAGE = 6;    // DAMAGE = 6
    private static final int UPGRADE_PLUS_DMG = 3;  // UPGRADE_PLUS_DMG = 4

    // /STAT DECLARATION/
    public Reroute(){
        super(ID, COST, TYPE, RARITY, TARGET);
        magicNumber = baseMagicNumber = 2;
        SecondMagicNumber = BaseSecondMagicNumber = 3;
        energyCosts.put(TypeEnergyHelper.Mana.Temporal,SecondMagicNumber);
        CardModifierManager.addModifier(this,new SCVTemporalCardMod());
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new RestoreAction(magicNumber));
        Wiz.applyToSelf(new RiposteCripple(p,p,SecondMagicNumber));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
            upgradeMagicNumber2(1);
            initializeDescription();
        }
    }
}