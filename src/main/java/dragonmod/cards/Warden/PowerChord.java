package dragonmod.cards.Warden;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.patches.FieldsField;

public class PowerChord extends AbstractWardenCard {


    // TEXT DECLARATION

    public static final String ID = PowerChord.class.getSimpleName(); // USE THIS ONE FOR THE TEMPLATE;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;  //   since they don't change much.
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;       //
    private static final int COST = 2;  // COST = 1
    private static final int UPGRADED_COST = 1; // UPGRADED_COST = 1

    private static final int DAMAGE = 9;    // DAMAGE = 6
    private static final int UPGRADE_PLUS_DMG = 2;  // UPGRADE_PLUS_DMG = 4

    // /STAT DECLARATION/


    public PowerChord(){
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage =DAMAGE;
        isMultiDamage = true;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int fieldcount = 0;
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!FieldsField.Fields.get(m).isEmpty()) {
                for (AbstractCard card : FieldsField.Fields.get(m)) {
                    fieldcount += 1;
                }
            }
        }
        for (int i = 0; i < fieldcount ; i++){
            AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p,multiDamage, DamageInfo.DamageType.NORMAL,AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeBlock(2);
            initializeDescription();
        }
    }
}