package dragonmod.cards.Warden;

import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.BranchingUpgradesCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.SCVTemporalCardMod;
import dragonmod.DragonMod;
import dragonmod.patches.FieldsField;
import dragonmod.patches.FieldsManager;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;
import dragonmod.util.onAttackedField;

import java.util.ArrayList;
import java.util.List;

public class MirrorForce extends AbstractWardenCard implements onAttackedField, BranchingUpgradesCard {


    // TEXT DECLARATION

    public static final String ID = MirrorForce.class.getSimpleName(); // USE THIS ONE FOR THE TEMPLATE;
    private static final UIStrings VentTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:Field");
    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON; //  Up to you, I like auto-complete on these
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;  //   since they don't change much.
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;       //
    private static final int COST = 1;  // COST = 1
    private static final int UPGRADED_COST = 1; // UPGRADED_COST = 1

    private static final int DAMAGE = 8;    // DAMAGE = 6
    private static final int UPGRADE_PLUS_DMG = 3;  // UPGRADE_PLUS_DMG = 4

    // /STAT DECLARATION/

    public MirrorForce(){
        super(ID, COST, TYPE, RARITY, TARGET);
        magicNumber = baseMagicNumber = 4;
        damage = baseDamage = 0;
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
                FieldsManager.addCard(MirrorForce.this,true,p);
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
        rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    public void branchUpgrade() {
        isInnate = true;
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        upgradeDamage(8);
        initializeDescription();
    }

    @Override
    public int AttachedOnAttacked(AbstractCreature owner, int dmg, DamageInfo info) {
        if (AbstractDungeon.actionManager.turnHasEnded && info.owner != owner && dmg > 0){
            Wiz.adp().limbo.group.add(this);
            Wiz.atb(new DamageAllEnemiesAction(AbstractDungeon.player,dmg+baseDamage, DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            energyCosts.put(TypeEnergyHelper.Mana.Temporal,energyCosts.get(TypeEnergyHelper.Mana.Temporal)-1);
            if (energyCosts.get(TypeEnergyHelper.Mana.Temporal) < 1){
                Wiz.atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        FieldsField.Fields.get(AbstractDungeon.player).remove(MirrorForce.this);
                        Wiz.atb(new UnlimboAction(MirrorForce.this));
                        if (!upgraded){
                            AbstractDungeon.player.discardPile.moveToExhaustPile(MirrorForce.this);
                        } else {
                            AbstractDungeon.player.discardPile.moveToDiscardPile(MirrorForce.this);
                        }
                        this.isDone = true;
                    }
                });
            }
            Wiz.dmg(owner,new DamageInfo(owner,dmg, DamageInfo.DamageType.THORNS));
            return  0;
        }
        return dmg;
    }
}