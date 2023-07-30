package dragonmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import dragonmod.DragonMod;
import dragonmod.actions.GainStressAction;
import dragonmod.util.Wiz;
import dragonmod.util.onLoseHPField;
import javassist.CtBehavior;

import java.util.ArrayList;

@SpirePatch(
        clz = AbstractPlayer.class,
        method = "damage"
)
public class StaggerPostFix {

    @SpireInsertPatch(
            locator = Locator.class,
            localvars = {"damageAmount"}
    )

    public static void StaggerPostFix(AbstractPlayer __instance, DamageInfo info, @ByRef int[] damageAmount) {
        if (DragonMod.renderStressUI){
            Wiz.att(new GainStressAction(damageAmount[0]));
        }
        for (AbstractCard field : FieldsField.Fields.get(__instance)){
            if (field instanceof onLoseHPField){
                ((onLoseHPField) field).AttachedOnAttacked(__instance);
            }
        }
    }
    private static class Locator extends SpireInsertLocator {
        private Locator() {
        }

        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(GameActionManager.class,"damageReceivedThisTurn");
            return offset(LineFinder.findInOrder(ctMethodToPatch, new ArrayList(), finalMatcher),0);
        }

        private static int[] offset(int[] originalArr, int offset) {
            for(int i = 0; i < originalArr.length; ++i) {
                originalArr[i] += offset;
            }

            return originalArr;
        }
    }
}
