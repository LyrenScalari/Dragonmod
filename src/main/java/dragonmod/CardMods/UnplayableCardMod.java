package dragonmod.CardMods;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;

public class UnplayableCardMod extends AbstractCardModifier {

    private UIStrings uiStrings =  CardCrawlGame.languagePack.getUIString("dragonmod:CardmodStrings");
    public UnplayableCardMod () {}
    public boolean isInherent(AbstractCard card) {
        return true;
    }
    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return uiStrings.TEXT[5] + rawDescription;
    }
    public boolean canPlayCard(AbstractCard card) {
        return false;
    }
    public boolean removeAtEndOfTurn(AbstractCard card) {
        return true;
    }
    @Override
    public AbstractCardModifier makeCopy() {
        return new UnplayableCardMod();
    }
}