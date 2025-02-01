package dragonmod.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import dragonmod.DragonMod;
import dragonmod.util.EnchantmentsManager;
import javassist.CtBehavior;

@SpirePatch2(
        clz = UseCardAction.class,
        method = "update"
)
public class EnchantmentsRedirectPatch {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static SpireReturn Insert(UseCardAction __instance) {
            AbstractCard targetcard = ReflectionHacks.getPrivate(__instance,UseCardAction.class,"targetCard");
            if (targetcard.hasTag(DragonMod.Enchantment) || targetcard.hasTag(EnchantmentsManager.Verse)) {
                __instance.isDone = true;
                return SpireReturn.Return(null);
            } else {
                return SpireReturn.Continue();
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractCard.class, "freeToPlayOnce");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
}
