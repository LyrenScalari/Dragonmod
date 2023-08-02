package dragonmod.cards.Rimedancer;

import com.megacrit.cardcrawl.cards.AbstractCard;
import dragonmod.cards.AbstractDragonCard;
import dragonmod.characters.TheRimedancer;

public abstract class AbstractRimedancerCard extends AbstractDragonCard {
    public AbstractRimedancerCard(final String id,
                                final int cost,
                                final AbstractCard.CardType type,
                                final AbstractCard.CardRarity rarity,
                                final AbstractCard.CardTarget target) {

        super(id, cost, type, TheRimedancer.Enums.Rimedancer_Cyan_COLOR, rarity, target);

    }
    public AbstractRimedancerCard(final String id,
                                final int cost,
                                final AbstractCard.CardType type,
                                final AbstractCard.CardRarity rarity,
                                final AbstractCard.CardTarget target, boolean colorless) {

        super(id, cost, type, AbstractCard.CardColor.COLORLESS, rarity, target);

    }
}