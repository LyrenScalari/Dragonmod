package dragonmod.cards.Warden;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.SCVTemporalCardMod;
import dragonmod.DragonMod;
import dragonmod.actions.RestoreAction;
import dragonmod.powers.Drifter.Panic;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;

;

public class VampiricVisage extends AbstractWardenCard {


    // TEXT DECLARATION

    public static final String ID = DragonMod.makeID(VampiricVisage.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;  //   since they don't change much.
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;       //

    private static final int COST = 1;  // COST = 1
    private static final int UPGRADED_COST = 1; // UPGRADED_COST = 1

    private static final int DAMAGE = 4;    // DAMAGE = 6
    private static final int UPGRADE_PLUS_DMG = 4;  // UPGRADE_PLUS_DMG = 4
    public VampiricVisage(){
        super(ID, COST, TYPE, RARITY, TARGET);
        SecondMagicNumber = BaseSecondMagicNumber = 3;
        magicNumber = baseMagicNumber = 5;
        energyCosts.put(TypeEnergyHelper.Mana.Temporal,SecondMagicNumber);
        CardModifierManager.addModifier(this,new SCVTemporalCardMod());
    }
    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new RestoreAction(SecondMagicNumber));
        Wiz.applyToEnemy(m,new Panic(m,p,magicNumber));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}