package dragonmod.cards.Warden;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import dragonmod.cards.AbstractDragonCard;
import dragonmod.characters.TheWarden;


abstract public class AbstractWardenCard extends AbstractDragonCard {
    // "How come BlazingBreath extends CustomCard and not DynamicCard like all the rest?"

    // Well every card, at the end of the day, extends CustomCard.
    // Abstract Default Card extends CustomCard and builds up on it, adding a second magic number. Your card can extend it and
    // bam - you can have a second magic number in that card (Learn Java inheritance if you want to know how that works).
    // Abstract Dynamic Card builds up on Abstract Default Card even more and makes it so that you don't need to add
    // the NAME and the DESCRIPTION into your card - it'll get it automatically. Of course, this functionality could have easily
    // Been added to the default card rather than creating a new Dynamic one, but was done so to deliberately.
    public static CardStrings tooltip = CardCrawlGame.languagePack.getCardStrings("theDragonkin:AbstractJusticarCard");
    public AbstractWardenCard(final String id,
                              final int cost,
                              final AbstractCard.CardType type,
                              final AbstractCard.CardRarity rarity,
                              final AbstractCard.CardTarget target) {

        super(id, cost, type, TheWarden.Enums.Warden_Bronze_COLOR, rarity, target);

    }
    public AbstractWardenCard(final String id,
                              final int cost,
                              final AbstractCard.CardType type,
                              final AbstractCard.CardRarity rarity,
                              final AbstractCard.CardTarget target, boolean colorless) {

        super(id, cost, type, AbstractCard.CardColor.COLORLESS, rarity, target);

    }
    public boolean Foreseen = false;
}