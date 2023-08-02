package dragonmod.cards.Warden;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsCenteredAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import dragonmod.actions.RewindAction;
import dragonmod.patches.FieldsField;
import dragonmod.util.Wiz;

import java.util.ArrayList;

public class SonicShock extends AbstractWardenCard {


    // TEXT DECLARATION

    public static final String ID = SonicShock.class.getSimpleName(); // USE THIS ONE FOR THE TEMPLATE;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;  //   since they don't change much.
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;       //
    private static final int COST = 1;  // COST = 1
    private static final int UPGRADED_COST = 1; // UPGRADED_COST = 1

    private static final int DAMAGE = 8;    // DAMAGE = 6
    private static final int UPGRADE_PLUS_DMG = 1;  // UPGRADE_PLUS_DMG = 4
    // /STAT DECLARATION/

    public SonicShock(){
        super(ID,COST,TYPE,RARITY,TARGET);
        baseDamage =DAMAGE;
        magicNumber = baseMagicNumber = 2;
    }
    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        if (!FieldsField.Fields.get(m).isEmpty()){
            AbstractCard attached;
            if (FieldsField.Fields.get(m).size() > 1){
                CardGroup Fields = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                for (AbstractCard c : FieldsField.Fields.get(m)){
                    Fields.addToBottom(c);
                }
                Wiz.atb(new SelectCardsCenteredAction(Fields.group,Manipstrings.EXTENDED_DESCRIPTION[5],false,(card)->true, List-> Wiz.atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        Wiz.applyToSelf(new DrawCardNextTurnPower(AbstractDungeon.player,1));
                        FieldsField.Fields.get(m).remove(List.get(0));
                        if (List.get(0).type != AbstractCard.CardType.POWER){
                            AbstractDungeon.player.drawPile.moveToBottomOfDeck(List.get(0));
                        }
                        isDone = true;
                    }
                })));
            } else {
                attached = FieldsField.Fields.get(m).get(AbstractDungeon.miscRng.random(0,FieldsField.Fields.get(m).size()-1));
                Wiz.atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        Wiz.applyToSelf(new DrawCardNextTurnPower(AbstractDungeon.player,1));
                        FieldsField.Fields.get(m).remove(attached);
                        if (attached.type != AbstractCard.CardType.POWER){
                            AbstractDungeon.player.drawPile.moveToBottomOfDeck(attached);
                        }
                        isDone = true;
                    }
                });
            }
                ArrayList<AbstractCard> canMove = new ArrayList<>(AbstractDungeon.player.discardPile.group);
                for (int i = 1; i <= magicNumber; i++){
                    if (canMove.isEmpty())
                        break;
                    Wiz.atb(new RewindAction(canMove.remove(AbstractDungeon.cardRng.random(canMove.size() - 1)),
                            AbstractDungeon.player.discardPile));
                }

        }
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}