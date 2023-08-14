package dragonmod.CardMods;

import basemod.abstracts.AbstractCardModifier;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PersistFields;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;

public class PersistCardmod extends AbstractCardModifier {

    private UIStrings uiStrings =  CardCrawlGame.languagePack.getUIString("dragonmod:CardmodStrings");
    public PersistCardmod () {}
    public boolean isInherent(AbstractCard card) {
        return true;
    }
    @Override
    public void onInitialApplication(AbstractCard card) {

    }
    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return uiStrings.TEXT[1] + PersistFields.persist.get(card) + " NL" + rawDescription;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new  PersistCardmod();
    }
}
