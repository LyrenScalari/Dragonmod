package DragonMod.cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;

import static DragonMod.DragonMod.makeID;
import static DragonMod.DragonMod.modID;

public class ExhaustMod extends AbstractCardModifier {
    public static String ID = makeID("ExhaustMod");
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(modID+":UIText");
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + " NL " + uiStrings.TEXT[1];
    }

    public boolean shouldApply(AbstractCard card) {
        return !card.exhaust;
    }

    public void onInitialApplication(AbstractCard card) {
        card.exhaust = true;
    }

    public AbstractCardModifier makeCopy() {
        return new ExhaustMod();
    }

    public String identifier(AbstractCard card) {
        return ID;
    }
}
