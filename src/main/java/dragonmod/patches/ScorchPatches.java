package dragonmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CannotCompileException;
import javassist.CtBehavior;

public class ScorchPatches {
    public interface StartofTurnPreBlock{
        public void atStartofTurnPreBlock();
    }
    @SpirePatch2(clz= GameActionManager.class,method = "getNextAction")
    public static class PlayerPatch{
        @SpireInsertPatch(locator=PlayerLocator.class)
        public static void ScorchInsert(GameActionManager __instance){
            for (AbstractPower p : AbstractDungeon.player.powers){
                if (p instanceof StartofTurnPreBlock){
                    ((StartofTurnPreBlock) p).atStartofTurnPreBlock();
                }
            }
        }
    }
    @SpirePatch2(clz= GameActionManager.class,method = "callEndOfTurnActions")
    public static class MonsterPatch{
        @SpirePrefixPatch
        public static void ScorchPrefix(GameActionManager __instance){
            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters){
                if (!m.isDeadOrEscaped()) {
                    for (AbstractPower p : m.powers) {
                        if (p instanceof StartofTurnPreBlock) {
                            ((StartofTurnPreBlock) p).atStartofTurnPreBlock();
                        }
                    }
                }
            }
        }
    }
    private static class PlayerLocator extends SpireInsertLocator{

        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher.NewExprMatcher finalMatcher = new Matcher.NewExprMatcher(WaitAction.class);
            return LineFinder.findAllInOrder(ctMethodToPatch,finalMatcher);
        }
    }
}
