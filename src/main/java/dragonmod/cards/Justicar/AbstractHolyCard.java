package dragonmod.cards.Justicar;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import dragonmod.DragonMod;
import dragonmod.powers.Dragonkin.SacrificePower;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractHolyCard extends AbstractJusticarCard {
    public int RadiantExchange = 5;

    public AbstractHolyCard(String id, int cost, AbstractCard.CardType type, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target) {
        super(id, cost, type, rarity, target);
        setOrbTexture(DragonMod.HOLY_ORB, DragonMod.HOLY_ORB_P);
    }
    public AbstractHolyCard(String id, int cost, AbstractCard.CardType type, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target , boolean colorless) {
        super(id, cost, type, rarity, target, true);
        setOrbTexture(DragonMod.HOLY_ORB, DragonMod.HOLY_ORB_P);
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        int bonus = 0;
        if (this.costForTurn > 0) {
            addToBot(new ApplyPowerAction(p, p, new SacrificePower(p, p, this.costForTurn*2)));
        } else if (costForTurn == -1){
            int energyused = EnergyPanel.totalCount;
            if (p.hasRelic(ChemicalX.ID)){
                energyused +=2;
            }
            addToBot(new ApplyPowerAction(p, p, new SacrificePower(p, p, energyused*2)));
        }
    }

    private static final UIStrings holyTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:HolyTooltip");
    @Override
    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo> retVal = new ArrayList<>();
        retVal.add(new TooltipInfo(holyTooltip.TEXT[0], holyTooltip.TEXT[1]));
        return retVal;
    }
    @Override
    public List<String> getCardDescriptors() {
        List<String> tags = new ArrayList<>();
        tags.add(BaseMod.getKeywordTitle("dragonmod:Holy"));
        tags.addAll(super.getCardDescriptors());
        return tags;
    }
}
