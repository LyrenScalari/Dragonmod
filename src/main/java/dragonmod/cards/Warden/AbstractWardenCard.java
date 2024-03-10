package dragonmod.cards.Warden;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import dragonmod.cards.AbstractDragonCard;
import dragonmod.characters.TheWarden;


abstract public class AbstractWardenCard extends AbstractDragonCard {
    public static CardStrings tooltip = CardCrawlGame.languagePack.getCardStrings("theDragonkin:AbstractJusticarCard");
    public static UIStrings AmethystBlossomString = CardCrawlGame.languagePack.getUIString("dragonmod:AmethystBlossom");
    public static UIStrings AmberBlossomString = CardCrawlGame.languagePack.getUIString("dragonmod:AmberBlossom");
    public AbstractWardenCard(final String id,
                              final int cost,
                              final AbstractCard.CardType type,
                              final AbstractCard.CardRarity rarity,
                              final AbstractCard.CardTarget target) {

        super(id, cost, type, TheWarden.Enums.Warden_Emerald_COLOR, rarity, target);

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