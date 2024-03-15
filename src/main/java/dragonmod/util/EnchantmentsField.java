package dragonmod.util;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.CardGroup;

@SpirePatch(
        cls = "com.megacrit.cardcrawl.core.AbstractCreature",
        method = "<class>"
)
public class EnchantmentsField {
    public static SpireField<CardGroup> Enchantments = new SpireField(() -> null);

    public EnchantmentsField() {
    }
}

