package dragonmod.patches.Orbs;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import dragonmod.interfaces.onEvokeOrbOrb;
import javassist.CtBehavior;


@SpirePatches({@SpirePatch(
        cls = "com.megacrit.cardcrawl.characters.AbstractPlayer",
        method = "evokeOrb"
), @SpirePatch(
        cls = "com.megacrit.cardcrawl.characters.AbstractPlayer",
        method = "evokeNewestOrb"
), @SpirePatch(
        cls = "com.megacrit.cardcrawl.characters.AbstractPlayer",
        method = "evokeWithoutLosingOrb"
)})
public class onEvokeOrbOrbPatch {
    @SpireInsertPatch(
            locator=EvokeLocator.class
    )
    public static void onEvokeOrbOrb() {
        for (AbstractOrb o : AbstractDungeon.player.orbs) {
            if (o instanceof onEvokeOrbOrb) {
                ((onEvokeOrbOrb) o).onEvokeOrb(AbstractDungeon.player.orbs.get(0));
            }
        }
    }

    private static class EvokeLocator extends SpireInsertLocator {
        private EvokeLocator() {
        }

        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractOrb.class, "onEvoke");
            int[] tmp = LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);
            return new int[]{tmp[0]};
        }
    }
}
