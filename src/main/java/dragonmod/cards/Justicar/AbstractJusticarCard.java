package dragonmod.cards.Justicar;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import dragonmod.cards.AbstractDragonCard;
import dragonmod.characters.TheJusticar;


public abstract class AbstractJusticarCard extends AbstractDragonCard {
    public AbstractJusticarCard(final String id,
                                final int cost,
                                final AbstractCard.CardType type,
                                final AbstractCard.CardRarity rarity,
                                final AbstractCard.CardTarget target) {

        super(id, cost, type, TheJusticar.Enums.Justicar_Red_COLOR, rarity, target);

    }
    public AbstractJusticarCard(final String id,
                                final int cost,
                                final AbstractCard.CardType type,
                                final AbstractCard.CardRarity rarity,
                                final AbstractCard.CardTarget target, boolean colorless) {

        super(id, cost, type, CardColor.COLORLESS, rarity, target);

    }
    public static UIStrings verseString = CardCrawlGame.languagePack.getUIString("dragonmod:Verse");
    public static UIStrings inspirationString = CardCrawlGame.languagePack.getUIString("dragonmod:Inspiration");
    public int realBaseDamage;
    public int realBaseMagic;
    private boolean needsArtRefresh = false;
    public Color renderColor = Color.WHITE.cpy();
    public boolean freeManaOnce = false;
}
