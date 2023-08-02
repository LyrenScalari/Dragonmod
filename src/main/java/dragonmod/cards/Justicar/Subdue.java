package dragonmod.cards.Justicar;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.powers.Dragonkin.SubduedPower;


public class Subdue extends AbstractHolyCard {

    public static final String ID = Subdue.class.getSimpleName();

    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;

    private static final int POTENCY = 0;
    private static final int UPGRADE_PLUS_DMG = 0;
    private static final int MAGIC = 1;
    private static final int UPGRADE_MAGIC = 0;

    public Subdue() {
        super(ID, COST, TYPE, RARITY, TARGET);
        damage = baseDamage = POTENCY;
        block = baseBlock = POTENCY;
        heal = baseHeal = POTENCY;
        baseMagicNumber = magicNumber = MAGIC;
        this.exhaust = true;

    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m,p,new SubduedPower(m,p,magicNumber)));
        super.use(p,m);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.selfRetain = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            updateCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}