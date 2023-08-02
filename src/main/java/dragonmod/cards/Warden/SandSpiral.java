package dragonmod.cards.Warden;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.actions.ScryCallbackAction;


public class SandSpiral extends AbstractWardenCard {


    // TEXT DECLARATION

    public static final String ID = SandSpiral.class.getSimpleName(); // USE THIS ONE FOR THE TEMPLATE;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON; //  Up to you, I like auto-complete on these
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;  //   since they don't change much.
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;       //
    private static final int COST = 1;  // COST = 1
    private static final int UPGRADED_COST = 1; // UPGRADED_COST = 1

    private static final int DAMAGE = 7;    // DAMAGE = 6
    private static final int UPGRADE_PLUS_DMG = 3;  // UPGRADE_PLUS_DMG = 4

    // /STAT DECLARATION/


    public SandSpiral(){
        super(ID,COST,TYPE,RARITY,TARGET);
        damage = baseDamage = 8;
        secondDamage = baseSecondDamage = 4;
        magicNumber = baseMagicNumber = 2;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL)));
        addToBot(new ScryCallbackAction(magicNumber, cards-> {
            for (AbstractCard card : cards){
                addToBot(new DamageAction(m,new DamageInfo(p,secondDamage, DamageInfo.DamageType.NORMAL)));
            }
        }));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            upgradeDamage(2);
            initializeDescription();
        }
    }
}