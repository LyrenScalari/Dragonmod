package dragonmod.patches.Orbs;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import dragonmod.interfaces.onDrawCard;
import javassist.CtBehavior;


@SpirePatch(clz = AbstractPlayer.class, method = "draw",
        paramtypez={int.class})
public class onCardDrawOrbPatch {
    @SpireInsertPatch(
            locator= EvokeLocator.class
    )
    public static void onCardDrawOrb(AbstractPlayer __instance) {
        for (AbstractOrb o : AbstractDungeon.player.orbs) {
            if (o instanceof onDrawCard) {
                ((onDrawCard) o).OnDrawCard();
            }
        }
        if (__instance.stance instanceof onDrawCard){
            ((onDrawCard) __instance.stance).OnDrawCard();
        }
    }

    private static class EvokeLocator extends SpireInsertLocator {
        private EvokeLocator() {
        }

        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractCard.class, "triggerWhenDrawn");
            int[] tmp = LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);
            return new int[]{tmp[0]};
        }
    }
}
