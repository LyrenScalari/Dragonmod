package dragonmod.cards.Warden;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.patches.FieldsField;
import dragonmod.util.Wiz;


public class StageDive extends AbstractWardenCard {


    // TEXT DECLARATION

    public static final String ID = DragonMod.makeID(StageDive.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;  //   since they don't change much.
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;       //
    private static final int COST = 2;  // COST = 1
    private static final int UPGRADED_COST = 1; // UPGRADED_COST = 1

    private static final int DAMAGE = 9;    // DAMAGE = 6
    private static final int UPGRADE_PLUS_DMG = 2;  // UPGRADE_PLUS_DMG = 4

    // /STAT DECLARATION/


    public StageDive(){
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage =DAMAGE;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int fieldcount = 0;
        if (!FieldsField.Fields.get(Wiz.adp()).isEmpty()) {
            for (AbstractCard card : FieldsField.Fields.get(Wiz.adp())) {
                Wiz.atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        FieldsField.Fields.get(p).remove(card);
                        if (card.type != AbstractCard.CardType.POWER){
                            AbstractDungeon.player.discardPile.moveToDiscardPile(card);
                        }
                        isDone = true;
                    }
                });
                fieldcount += 1;
            }
        }
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!FieldsField.Fields.get(m).isEmpty()) {
                for (AbstractCard card : FieldsField.Fields.get(m)) {
                    Wiz.atb(new AbstractGameAction() {
                        @Override
                        public void update() {
                            FieldsField.Fields.get(mo).remove(card);
                            if (card.type != AbstractCard.CardType.POWER){
                                AbstractDungeon.player.discardPile.moveToDiscardPile(card);
                            }
                            isDone = true;
                        }
                    });
                    fieldcount += 1;
                }
            }
        }
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage+(damage*fieldcount), damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
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
