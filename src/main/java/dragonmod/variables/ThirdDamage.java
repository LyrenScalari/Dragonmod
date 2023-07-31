package dragonmod.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import dragonmod.cards.BaseCard;

import static dragonmod.DragonMod.makeID;


public class ThirdDamage extends DynamicVariable {

    //For in-depth comments, check the other variable(DefaultCustomVariable). It's nearly identical.

    @Override
    public String key() {
        return makeID("D3");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return ((BaseCard) card).isThirdDamageModified;

    }

    @Override
    public int value(AbstractCard card) {
        return ((BaseCard) card).ThirdDamage;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return ((BaseCard) card).baseThirdDamage;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return ((BaseCard) card).upgradedThirdDamage;
    }
}