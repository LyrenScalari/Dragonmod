package dragonmod.cards.Warden;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.cards.AbstractCard;

public abstract class AbstractReflexiveCard  extends AbstractWardenCard{
    public AbstractReflexiveCard Amber;
    public AbstractReflexiveCard Amethyst;
    public AbstractReflexiveCard Emerald;
    public AbstractReflexiveCard(final String id,
                              final int cost,
                              final AbstractCard.CardType type,
                              final AbstractCard.CardRarity rarity,
                              final AbstractCard.CardTarget target) {

        super(id, cost, type, rarity, target);

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
