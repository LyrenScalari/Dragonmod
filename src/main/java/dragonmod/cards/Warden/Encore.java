package dragonmod.cards.Warden;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsCenteredAction;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.BranchingUpgradesCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.patches.FieldsField;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;

import java.util.ArrayList;

public class Encore extends AbstractWardenCard implements BranchingUpgradesCard {


    // TEXT DECLARATION

    public static final String ID = (Encore.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    private static final UIStrings VentTooltip = CardCrawlGame.languagePack.getUIString("theDragonkin:Field");
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;  //   since they don't change much.
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;       //
    private static final int COST = 1;  // COST = 1
    private static final int UPGRADED_COST = 1; // UPGRADED_COST = 1

    private static final int DAMAGE = 8;    // DAMAGE = 6
    private static final int UPGRADE_PLUS_DMG = 3;  // UPGRADE_PLUS_DMG = 4

    // /STAT DECLARATION/

    public Encore(){
        super(ID, COST, TYPE, RARITY, TARGET);
        damage = baseDamage = 8;
        magicNumber = baseMagicNumber = 1;
        BaseSecondMagicNumber = SecondMagicNumber = 1;
    }
    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        ArrayList<AbstractCard> attached = new ArrayList<>(FieldsField.Fields.get(p));
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters){
            if (!mo.isDeadOrEscaped() && FieldsField.Fields.get(mo) != null){
                attached.addAll(FieldsField.Fields.get(mo));
            }
        }
        if (attached.size() <= SecondMagicNumber){
            for (AbstractCard c : attached){
                ((AbstractWardenCard)c).energyCosts.put(TypeEnergyHelper.Mana.Temporal,((AbstractWardenCard) c).energyCosts.get(TypeEnergyHelper.Mana.Temporal) + magicNumber);
                c.initializeDescription();
            }
        } else {
            Wiz.atb(new SelectCardsCenteredAction(attached,SecondMagicNumber,Manipstrings.EXTENDED_DESCRIPTION[6], List-> {
                for (AbstractCard c : List){
                    ((AbstractWardenCard)c).energyCosts.put(TypeEnergyHelper.Mana.Temporal,((AbstractWardenCard) c).energyCosts.get(TypeEnergyHelper.Mana.Temporal) + magicNumber);
                    c.initializeDescription();
                }
            }));
        }
    }


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
        rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
        upgradeMagicNumber2(1);
        initializeDescription();
    }

    public void branchUpgrade() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        upgradeMagicNumber(1);
        initializeDescription();
    }
}