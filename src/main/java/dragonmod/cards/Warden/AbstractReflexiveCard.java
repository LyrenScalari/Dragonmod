package dragonmod.cards.Warden;

import basemod.helpers.TooltipInfo;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import dragonmod.patches.StanceChangePatch;
import dragonmod.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractReflexiveCard extends AbstractWardenCard{
    public AbstractReflexiveCard Amber;
    public AbstractReflexiveCard Amethyst;
    public AbstractReflexiveCard Emerald;
    public static UIStrings reflexiveString = CardCrawlGame.languagePack.getUIString("dragonmod:Reflexive");
    public AbstractReflexiveCard(final String id,
                              final int cost,
                              final AbstractCard.CardType type,
                              final AbstractCard.CardRarity rarity,
                              final AbstractCard.CardTarget target) {

        super(id, cost, type, rarity, target);

    }

    @Override
    public List<String> getCardDescriptors() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(reflexiveString.TEXT[0]);
        return retVal;
    }

    @Override
    public List<TooltipInfo> getCustomTooltipsTop() {
        ArrayList<TooltipInfo> retVal = new ArrayList<>();
        retVal.add(new TooltipInfo(reflexiveString.TEXT[0],reflexiveString.TEXT[1]));
        return retVal;
    }

    public AbstractCard getAmberCard(){
        return Amber;
    }
    public AbstractCard getAmethystCard(){
        return Amethyst;
    }
    public AbstractCard getEmeraldCard(){
        return Emerald;
    }
    public void triggerWhenDrawn() {
        StanceChangePatch.swapThemOver(this, Wiz.Player().stance.ID);
    }
    public void setReflectivePairing(AbstractReflexiveCard AmberCard, AbstractReflexiveCard AmethystCard) {
        Emerald = this;
        Amethyst = (AbstractReflexiveCard) AmethystCard.makeStatEquivalentCopy();
        Amber = (AbstractReflexiveCard) AmberCard.makeStatEquivalentCopy();
        Amethyst.Amethyst = Amethyst;
        Amethyst.Amber = Amber;
        Amethyst.Emerald = this;
        Amber.Amethyst = Amethyst;
        Amber.Amber = Amber;
        Amber.Emerald = this;
        MultiCardPreview.add(Emerald,true,Amber,Amethyst);
        MultiCardPreview.add(Amber,true,Emerald,Amethyst);
        MultiCardPreview.add(Amethyst,true,Emerald,Amber);
    }
}
