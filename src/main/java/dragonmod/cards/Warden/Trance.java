package dragonmod.cards.Warden;

import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
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
import dragonmod.util.FieldCard;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public class Trance extends AbstractWardenCard implements FieldCard {

    public static final String ID = Trance.class.getSimpleName(); // USE THIS ONE FOR THE TEMPLATE;
    private static final UIStrings VentTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:Field");
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.SPECIAL; //  Up to you, I like auto-complete on these
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;  //   since they don't change much.
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.STATUS;       //

    private static final int COST = -2;  // COST = 1
    private static final int UPGRADED_COST = 1; // UPGRADED_COST = 1

    private static final int DAMAGE = 6;    // DAMAGE = 6
    private static final int UPGRADE_PLUS_DMG = 3;  // UPGRADE_PLUS_DMG = 4
    // /STAT DECLARATION/
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }
    public Trance(){
        super(ID, COST, TYPE, RARITY, TARGET,true);
        block = baseBlock = 4;
        damage = baseDamage = 4;
        BaseSecondMagicNumber = SecondMagicNumber = damage;
        magicNumber = baseMagicNumber = block;
        tags.add(DragonMod.Field);
        energyCosts.put(TypeEnergyHelper.Mana.Temporal,4);
        CardModifierManager.addModifier(this,new SCVTemporalCardMod());
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
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

    public void AttachedTurnStart(AbstractCreature owner){
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(owner, new DamageInfo(AbstractDungeon.player, SecondMagicNumber, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        addToBot(new GainBlockAction(AbstractDungeon.player,magicNumber));
        energyCosts.put(TypeEnergyHelper.Mana.Temporal,energyCosts.get(TypeEnergyHelper.Mana.Temporal)-1);
        initializeDescription();
        if (energyCosts.get(TypeEnergyHelper.Mana.Temporal) < 1) {
            Wiz.atb(new AbstractGameAction() {
                @Override
                public void update() {
                    if (!FieldsManager.didbacklash) {
                        FieldsField.Fields.get(owner).remove(Trance.this);
                        AbstractDungeon.player.discardPile.moveToDiscardPile(Trance.this);
                        energyCosts.put(TypeEnergyHelper.Mana.Temporal,4);
                        Trance.this.initializeDescription();
                    }
                    isDone = true;
                }
            });
        }
    }
    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            initializeDescription();
        }
    }
}

