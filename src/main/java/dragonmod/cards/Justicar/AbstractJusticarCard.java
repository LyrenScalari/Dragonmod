package dragonmod.cards.Justicar;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import dragonmod.cards.AbstractDragonCard;
import dragonmod.characters.TheJusticar;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;


public abstract class AbstractJusticarCard extends AbstractDragonCard {
    public static CardStrings tooltip = CardCrawlGame.languagePack.getCardStrings("theDragonkin:AbstractJusticarCard");
    public AbstractJusticarCard(final String id,
                                final int cost,
                                final AbstractCard.CardType type,
                                final AbstractCard.CardRarity rarity,
                                final AbstractCard.CardTarget target) {

        super(id, languagePack.getCardStrings(id).NAME, cost, languagePack.getCardStrings(id).DESCRIPTION, type, TheJusticar.Enums.Justicar_Red_COLOR, rarity, target);

    }
    public AbstractJusticarCard(final String id,
                                final int cost,
                                final AbstractCard.CardType type,
                                final AbstractCard.CardRarity rarity,
                                final AbstractCard.CardTarget target, boolean colorless) {

        super(id, languagePack.getCardStrings(id).NAME, cost, languagePack.getCardStrings(id).DESCRIPTION, type, CardColor.COLORLESS, rarity, target);

    }
    public int realBaseDamage;
    public int realBaseMagic;
    private boolean needsArtRefresh = false;
    public Color renderColor = Color.WHITE.cpy();
    public boolean freeManaOnce = false;
}