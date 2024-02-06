package dragonmod.patches.Orbs;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.interfaces.atLockonFinalReceive;
import dragonmod.interfaces.atLockonReceive;
import javassist.CtBehavior;

@SpirePatch2(clz = AbstractOrb.class, method = "applyLockOn")
public class applyLockonPatch {
    @SpireInsertPatch(locator= EvokeLocator.class,localvars = {"retVal"})
    public static SpireReturn ApplyLockOnPatch(int __result, AbstractCreature target, int dmg,int retVal) {
        for (AbstractPower p : target.powers){
            if (p instanceof atLockonReceive){
                retVal = (int) ((atLockonReceive) p).applyLockOn(target,retVal);
            }
        }
        for (AbstractPower p : target.powers){
            if (p instanceof atLockonFinalReceive){
                retVal = (int) ((atLockonFinalReceive) p).applyLockOnFinal(target,retVal);
            }
        }
        return SpireReturn.Continue();
    }

    private static class EvokeLocator extends SpireInsertLocator {
        private EvokeLocator() {
        }

        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractCreature.class, "hasPower");
            int[] tmp = LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);
            return new int[]{tmp[0]};
        }
    }
}
