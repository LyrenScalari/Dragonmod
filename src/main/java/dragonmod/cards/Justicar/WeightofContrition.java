package dragonmod.cards.Justicar;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.powers.Dragonkin.PenancePower;

public class WeightofContrition extends AbstractHolyCard {
    public static final String ID = WeightofContrition.class.getSimpleName(); // USE THIS ONE FOR THE TEMPLATE;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;  //   since they don't change much.
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;       //
    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    private static final int DAMAGE = 5;
    private static final int UPGRADE_PLUS_DMG = 0;

    // /STAT DECLARATION/


    public WeightofContrition() {
        super(ID,COST,TYPE,RARITY,TARGET);
        baseDamage = damage = DAMAGE;
        magicNumber = baseMagicNumber = 2;
        SecondMagicNumber = BaseSecondMagicNumber = 3;
        RadiantExchange = 3;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new PenancePower(m, p, SecondMagicNumber)));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                PenancePower.Power += SecondMagicNumber;
                isDone = true;
            }
        });

        super.use(p,m);
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
            initializeDescription();
        }
    }
}