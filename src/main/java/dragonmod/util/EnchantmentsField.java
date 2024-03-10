package dragonmod.util;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.List;

@SpirePatch(
        cls = "com.megacrit.cardcrawl.core.AbstractCreature",
        method = "<class>"
)
public class EnchantmentsField {
    public static SpireField<List<AbstractCard>> Enchantments = new SpireField(() -> null);

    public EnchantmentsField() {
    }
}

