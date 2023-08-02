package dragonmod.cards.Warden;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.BranchingUpgradesCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.SCVTemporalCardMod;
import dragonmod.actions.RestoreAction;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;


public class PowerupPunch extends AbstractWardenCard implements BranchingUpgradesCard {


    // TEXT DECLARATION

    public static final String ID = PowerupPunch.class.getSimpleName(); // USE THIS ONE FOR THE TEMPLATE;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;  //   since they don't change much.
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;       //
    private static final int COST = 1;  // COST = 1
    private static final int UPGRADED_COST = 1; // UPGRADED_COST = 1

    private static final int DAMAGE = 6;    // DAMAGE = 6
    private static final int UPGRADE_PLUS_DMG = 3;  // UPGRADE_PLUS_DMG = 4

    // /STAT DECLARATION/


    public PowerupPunch(){
        super(ID, COST, TYPE, RARITY, TARGET);
        damage = baseDamage = 9;
        magicNumber = baseMagicNumber = 6;
        SecondMagicNumber = BaseSecondMagicNumber = 6;
        energyCosts.put(TypeEnergyHelper.Mana.Temporal,SecondMagicNumber);
        CardModifierManager.addModifier(this,new SCVTemporalCardMod());
    }
    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL));
        Wiz.atb(new RestoreAction(SecondMagicNumber));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            if (isBranchUpgrade()) {
                branchUpgrade();
            } else {
                baseUpgrade();
            }
        }
    }

    public void baseUpgrade() {
        upgradeDamage(5);
        initializeDescription();
    }

    public void branchUpgrade() {
        upgradeBaseCost(2);
        upgradeDamage(14);
        upgradeMagicNumber(1);
        upgradeMagicNumber2(12);
        initializeDescription();
    }
}