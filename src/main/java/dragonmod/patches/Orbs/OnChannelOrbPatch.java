package dragonmod.patches.Orbs;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import javassist.CtBehavior;

@SpirePatch(clz = AbstractPlayer.class, method = "channelOrb")
public class OnChannelOrbPatch {
    @SpireInsertPatch(
            locator= EvokeLocator.class,
            localvars = {"orbToSet"}
    )
    public static void onEvokeOrbOrb(AbstractPlayer __instance, AbstractOrb orbToSet) {
    }

    private static class EvokeLocator extends SpireInsertLocator {
        private EvokeLocator() {
        }

        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractOrb.class,"applyFocus");
            int[] tmp = LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);
            return new int[]{tmp[0]};
        }
    }
}