package dragonmod.cards.Warden;

import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.BranchingUpgradesCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.SCVTemporalCardMod;
import dragonmod.DragonMod;
import dragonmod.actions.GainStressAction;
import dragonmod.patches.FieldsField;
import dragonmod.patches.FieldsManager;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;
import dragonmod.util.onAttackedField;

import java.util.ArrayList;
import java.util.List;

public class Gravitas extends AbstractWardenCard implements onAttackedField, BranchingUpgradesCard {


    // TEXT DECLARATION

    public static final String ID = DragonMod.makeID(Gravitas.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    private static final UIStrings VentTooltip = CardCrawlGame.languagePack.getUIString("theDragonkin:Field");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.BASIC; //  Up to you, I like auto-complete on these
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;  //   since they don't change much.
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;       //
    private static final int COST = 1;  // COST = 1
    private static final int UPGRADED_COST = 1; // UPGRADED_COST = 1

    private static final int DAMAGE = 8;    // DAMAGE = 6
    private static final int UPGRADE_PLUS_DMG = 3;  // UPGRADE_PLUS_DMG = 4

    // /STAT DECLARATION/

    public Gravitas(){
        super(ID, COST, TYPE, RARITY, TARGET);
        magicNumber = baseMagicNumber = 5;
        exhaust = true;
        tags.add(DragonMod.Field);
        energyCosts.put(TypeEnergyHelper.Mana.Temporal,1);
        CardModifierManager.addModifier(this,new SCVTemporalCardMod());
    }
    @Override
    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo> tips = new ArrayList<>();
        tips.add(new TooltipInfo(VentTooltip.TEXT[0],VentTooltip.TEXT[1]));
        return tips;
    }
    @Override
    public List<String> getCardDescriptors() {
        List<String> retVal = new ArrayList<>();
        retVal.add(VentTooltip.TEXT[0]);
        return retVal;
    }
    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                FieldsManager.addCard(Gravitas.this,true,p);
                isDone = true;
            }
        });
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
        exhaust = false;
        upgradeMagicNumber(-2);
        rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    public void branchUpgrade() {
        isInnate = true;
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        upgradeBaseCost(0);
        initializeDescription();
    }

    @Override
    public int AttachedOnAttacked(AbstractCreature owner,int dmg,DamageInfo info) {
        if (AbstractDungeon.actionManager.turnHasEnded && info.owner != owner && dmg > 0){
            Wiz.adp().limbo.group.add(this);
            energyCosts.put(TypeEnergyHelper.Mana.Temporal,energyCosts.get(TypeEnergyHelper.Mana.Temporal)-1);
            if (energyCosts.get(TypeEnergyHelper.Mana.Temporal) < 1){
                Wiz.atb(new GainStressAction(magicNumber));
                Wiz.atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        FieldsField.Fields.get(AbstractDungeon.player).remove(Gravitas.this);
                        Wiz.atb(new UnlimboAction(Gravitas.this));
                        if (!upgraded){
                            AbstractDungeon.player.discardPile.moveToExhaustPile(Gravitas.this);
                        } else {
                            AbstractDungeon.player.discardPile.moveToDiscardPile(Gravitas.this);
                        }
                        this.isDone = true;
                    }
                });
            }
            return 0;
        } else {
            return dmg;
        }
    }
}