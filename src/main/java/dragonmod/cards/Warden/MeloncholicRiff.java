package dragonmod.cards.Warden;

import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import dragonmod.CardMods.SCVTemporalCardMod;
import dragonmod.DragonMod;
import dragonmod.patches.FieldsField;
import dragonmod.patches.FieldsManager;
import dragonmod.util.FieldCard;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public class MeloncholicRiff extends AbstractWardenCard implements FieldCard {


    // TEXT DECLARATION

    public static final String ID = MeloncholicRiff.class.getSimpleName(); // USE THIS ONE FOR THE TEMPLATE;
    private static final UIStrings VentTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:Field");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;  //   since they don't change much.
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;       //
    private static final int COST = 1;  // COST = 1
    private static final int UPGRADED_COST = 1; // UPGRADED_COST = 1

    private static final int DAMAGE = 6;    // DAMAGE = 6
    private static final int UPGRADE_PLUS_DMG = 3;  // UPGRADE_PLUS_DMG = 4

    // /STAT DECLARATION/

    public MeloncholicRiff(){
        super(ID, COST, TYPE, RARITY, TARGET);
        magicNumber = baseMagicNumber = 2;
        SecondMagicNumber =BaseSecondMagicNumber = 3;
        tags.add(DragonMod.Field);
        energyCosts.put(TypeEnergyHelper.Mana.Temporal,3);
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
                FieldsManager.addCard(MeloncholicRiff.this,true,m);
                isDone = true;
            }
        });
    }

    public void AttachedTurnStart(AbstractCreature owner){
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters){
            if (m != owner && !FieldsField.Fields.get(m).isEmpty()){
                Wiz.applyToEnemy(m,new WeakPower(m,magicNumber,false));
            }
            energyCosts.put(TypeEnergyHelper.Mana.Temporal,energyCosts.get(TypeEnergyHelper.Mana.Temporal)-1);
            initializeDescription();
            if (energyCosts.get(TypeEnergyHelper.Mana.Temporal) < 1) {
                Wiz.atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        if (!FieldsManager.didbacklash) {
                            FieldsField.Fields.get(owner).remove(MeloncholicRiff.this);
                            AbstractDungeon.player.discardPile.moveToDiscardPile(MeloncholicRiff.this);
                            energyCosts.put(TypeEnergyHelper.Mana.Temporal,magicNumber);
                            MeloncholicRiff.this.initializeDescription();
                        }
                        isDone = true;
                    }
                });
            }
        }

    }
    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            energyCosts.put(TypeEnergyHelper.Mana.Temporal,4);
            upgradeMagicNumber2(1);
            initializeDescription();
        }
    }
}
