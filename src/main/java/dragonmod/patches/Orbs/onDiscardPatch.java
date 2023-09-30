package dragonmod.patches.Orbs;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import dragonmod.util.HymnManager;
import dragonmod.util.AbstractNotOrb;
import dragonmod.util.AbstractRune;
import javassist.CtBehavior;


@SpirePatch(clz = DiscardAction.class, method = "update")
public class onDiscardPatch {
    @SpireInsertPatch(
            locator= EvokeLocator.class
    )
    public static void onDiscardPatch(DiscardAction __instance) {
        boolean endturn = ReflectionHacks.getPrivate(__instance,DiscardAction.class,"endTurn");
        if (!endturn) {
            for (AbstractNotOrb o : HymnManager.ActiveVerses) {
                if (o instanceof AbstractRune) {
                    ((AbstractRune) o).onManualDiscard();
                }
            }
        }
    }

    private static class EvokeLocator extends SpireInsertLocator {
        private EvokeLocator() {
        }

        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(GameActionManager.class, "incrementDiscard");
            int[] tmp = LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);
            return new int[]{tmp[0]};
        }
    }
}
