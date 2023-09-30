package dragonmod.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import dragonmod.DragonMod;
import dragonmod.cards.BaseCard;


public class SecondDamage extends DynamicVariable {

    //For in-depth comments, check the other variable(DefaultCustomVariable). It's nearly identical.

    @Override
    public String key() {
        return DragonMod.modID+":D2";
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return ((BaseCard) card).isSecondDamageModified;

    }

    @Override
    public int value(AbstractCard card) {
        return ((BaseCard) card).secondDamage;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return ((BaseCard) card).baseSecondDamage;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return ((BaseCard) card).upgradedSecondDamage;
    }
}
