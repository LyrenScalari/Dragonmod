package dragonmod.cards.Warden;

import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.SCVTemporalCardMod;
import dragonmod.DragonMod;
import dragonmod.actions.GainStressAction;
import dragonmod.util.TypeEnergyHelper;

import java.util.ArrayList;
import java.util.List;

public class PhaseOut extends AbstractWardenCard {


    // TEXT DECLARATION

    public static final String ID = DragonMod.makeID(PhaseOut.class.getSimpleName());
    private static final UIStrings StabilityTooltip = CardCrawlGame.languagePack.getUIString("theDragonkin:StabilityTooltip");
    private static final UIStrings VentTooltip = CardCrawlGame.languagePack.getUIString("theDragonkin:TemporalTooltip");
    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    private static final int COST = 2;
    private static final int BLOCK = 12;
    private static final int UPGRADE_PLUS_BLOCK = 4;


    // /STAT DECLARATION/

    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo> retVal = new ArrayList<>();
        retVal.add(new TooltipInfo(VentTooltip.TEXT[0],VentTooltip.TEXT[1]));
        retVal.add(new TooltipInfo(StabilityTooltip.TEXT[0],StabilityTooltip.TEXT[1]));
        return retVal;
    }
    @Override
    public List<String> getCardDescriptors() {
        List<String> retVal = new ArrayList<>();
        retVal.add(VentTooltip.TEXT[0]);
        return retVal;
    }
    public PhaseOut() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseBlock = BLOCK;
        energyCosts.put(TypeEnergyHelper.Mana.Temporal,4);
        CardModifierManager.addModifier(this,new SCVTemporalCardMod());
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        addToBot(new GainStressAction(this));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            initializeDescription();
        }
    }
}
