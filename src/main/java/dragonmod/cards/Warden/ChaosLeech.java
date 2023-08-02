package dragonmod.cards.Warden;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.SCVTemporalCardMod;
import dragonmod.actions.RestoreAction;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;


public class ChaosLeech extends AbstractWardenCard {


    // TEXT DECLARATION

    public static final String ID = ChaosLeech.class.getSimpleName(); // USE THIS ONE FOR THE TEMPLATE;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON; //  Up to you, I like auto-complete on these
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;  //   since they don't change much.
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;       //
    private static final int COST = 2;  // COST = 1
    private static final int UPGRADED_COST = 1; // UPGRADED_COST = 1

    private static final int DAMAGE = 6;    // DAMAGE = 6
    private static final int UPGRADE_PLUS_DMG = 3;  // UPGRADE_PLUS_DMG = 4

    // /STAT DECLARATION/
    public ChaosLeech(){
        super(ID, COST, TYPE, RARITY, TARGET);
        damage = baseDamage = 12;
        magicNumber = baseMagicNumber = 10;
        energyCosts.put(TypeEnergyHelper.Mana.Temporal,magicNumber);
        CardModifierManager.addModifier(this,new SCVTemporalCardMod());
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m,new DamageInfo(p,baseDamage, DamageInfo.DamageType.NORMAL));
        Wiz.atb(new RestoreAction(magicNumber));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
            upgradeDamage(2);
            initializeDescription();
        }
    }
}