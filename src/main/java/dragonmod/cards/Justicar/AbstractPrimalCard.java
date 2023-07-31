package dragonmod.cards.Justicar;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import dragonmod.DragonMod;
import dragonmod.powers.Dragonkin.Scorchpower;
import dragonmod.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPrimalCard extends AbstractJusticarCard {


    public AbstractPrimalCard(String id, int cost, AbstractCard.CardType type, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target) {
        super(id, cost, type, rarity, target);
        setOrbTexture(DragonMod.PRIMAL_ORB, DragonMod.PRIMAL_ORB_P);
    }
    @Override
    public List<String> getCardDescriptors() {
        List<String> tags = new ArrayList<>();
        tags.add(BaseMod.getKeywordTitle("thedragonkin:Primal"));
        tags.addAll(super.getCardDescriptors());
        return tags;
    }
    private static final UIStrings holyTooltip = CardCrawlGame.languagePack.getUIString("theDragonkin:PrimalTooltip");
    @Override
    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo> retVal = new ArrayList<>();
        retVal.add(new TooltipInfo(holyTooltip.TEXT[0], holyTooltip.TEXT[1]));
        return retVal;
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.costForTurn > 0) {
            for (int i = 0; i < costForTurn; i++){
                AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
                Wiz.applyToEnemyTop(target,new Scorchpower(target,p,1));
            }
        } else if (costForTurn == -1){
            int energyused = EnergyPanel.totalCount;
            if (p.hasRelic(ChemicalX.ID)){
                energyused +=2;
            }
            for (int i = 0; i < energyused; i++){
                AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
                Wiz.applyToEnemyTop(target,new Scorchpower(target,p,1));
            }
        }
    }
}