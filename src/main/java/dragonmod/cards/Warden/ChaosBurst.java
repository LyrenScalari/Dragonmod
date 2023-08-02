package dragonmod.cards.Warden;

import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
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
import dragonmod.patches.FieldsManager;
import dragonmod.util.FieldCard;
import dragonmod.util.TypeEnergyHelper;

import java.util.ArrayList;
import java.util.List;

;

public class ChaosBurst extends AbstractWardenCard implements FieldCard {


    // TEXT DECLARATION

    public static final String ID = ChaosBurst.class.getSimpleName(); // USE THIS ONE FOR THE TEMPLATE;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;  //   since they don't change much.
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;       //
    private static final UIStrings VentTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:Field");
    private static final int COST = 2;  // COST = 1
    private static final int UPGRADED_COST = 1; // UPGRADED_COST = 1

    private static final int DAMAGE = 7;    // DAMAGE = 6
    private static final int UPGRADE_PLUS_DMG = 3;  // UPGRADE_PLUS_DMG = 4
    // /STAT DECLARATION/
    public ChaosBurst(){
        super(ID, COST, TYPE, RARITY, TARGET);
        magicNumber = baseMagicNumber = 3;
        damage = baseDamage = 9;
        block = baseBlock = 9;
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
                FieldsManager.addCard(ChaosBurst.this,true,p);
                isDone = true;
            }
        });
    }

    public void AttachedTurnStart(AbstractCreature owner){
        for (int i = 0; i < energyCosts.get(TypeEnergyHelper.Mana.Temporal); i++){
            int chance = AbstractDungeon.miscRng.random(0,2);
            switch (chance){
                case 0 :
                    addToBot(new DamageRandomEnemyAction(new DamageInfo(owner,baseDamage, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.LIGHTNING));
                    break;
                case 1 :
                    addToBot(new GainBlockAction(owner,block));
                    break;
                case 2:
                    addToBot(new LoseHPAction(owner,owner,magicNumber));
                    break;
            }
        }
        energyCosts.put(TypeEnergyHelper.Mana.Temporal,energyCosts.get(TypeEnergyHelper.Mana.Temporal)+1);
        initializeDescription();
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(-1);
            upgradeBlock(2);
            upgradeDamage(2);
            initializeDescription();
        }
    }
}