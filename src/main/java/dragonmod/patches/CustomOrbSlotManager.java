package dragonmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.FocusPower;
import dragonmod.orbs.CrystalOrbSlot;
import dragonmod.util.Wiz;
import javassist.CtBehavior;

import java.util.Collections;

public class CustomOrbSlotManager {
    @SpirePatch(
            cls = "com.megacrit.cardcrawl.orbs.AbstractOrb",
            method = "<class>"
    )
    public static class SlotFields{
        public static SpireField<Boolean> Crystal = new SpireField(() -> false);
        public SlotFields(){}
    }

    @SpirePatch2(clz = AbstractPlayer.class, method = "channelOrb")
    public static class ChannelPatch{
        @SpireInsertPatch(
                locator= ChannelLocator.class,
                localvars = {"orbToSet","index"}
        )
        public static void onChannelOrb(AbstractPlayer __instance, AbstractOrb orbToSet,int index) {
            if (__instance.orbs.get(index) instanceof CrystalOrbSlot) {
                CustomOrbSlotManager.SlotFields.Crystal.set(orbToSet,true);
            }
        }
        public static class ChannelLocator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class,"orbs");
                int[] tmp = LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);
                return new int[]{tmp[3]};
            }
        }
    }

    @SpirePatch2(clz = AbstractPlayer.class, method = "evokeOrb")
    public static class EvokePatch{
        @SpireInsertPatch(
                locator= EvokeLocator.class
        )
        public static SpireReturn<Void> onEvokeOrb(AbstractPlayer __instance) {
            if (SlotFields.Crystal.get(__instance.orbs.get(0))) {
                ((AbstractOrb)__instance.orbs.get(0)).onEvoke();
                AbstractOrb CrystalOrb = __instance.orbs.get(0);
                Wiz.applyToSelfTempstart(new FocusPower(AbstractDungeon.player,1));
                Wiz.att(new AbstractGameAction() {
                    @Override
                    public void update() {
                        __instance.maxOrbs--;
                        __instance.orbs.remove(CrystalOrb);
                        isDone = true;
                    }
                });
                int i;
                for(i = 1; i < __instance.orbs.size(); ++i) {
                    Collections.swap(__instance.orbs, i, i - 1);
                }
                for(i = 0; i < __instance.orbs.size(); ++i) {
                    ((AbstractOrb)__instance.orbs.get(i)).setSlot(i, __instance.maxOrbs);
                }
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
        private static class EvokeLocator extends SpireInsertLocator {
            private EvokeLocator() {
            }

            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractOrb.class,"onEvoke");
                int[] tmp = LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);
                return new int[]{tmp[0]};
            }
        }
    }
    @SpirePatch2(clz = AbstractPlayer.class, method = "evokeWithoutLosingOrb")
    public static class EvokePatch2{
        @SpirePostfixPatch
        public static SpireReturn<Void> onEvokeOrb(AbstractPlayer __instance) {
            if (SlotFields.Crystal.get(__instance.orbs.get(__instance.orbs.size() - 1))) {
                __instance.maxOrbs--;
                __instance.orbs.remove(__instance.orbs.get(__instance.orbs.size() - 1));
                int i;
                for(i = 1; i < __instance.orbs.size(); ++i) {
                    Collections.swap(__instance.orbs, i, i - 1);
                }
                for(i = 0; i < __instance.orbs.size(); ++i) {
                    ((AbstractOrb)__instance.orbs.get(i)).setSlot(i, __instance.maxOrbs);
                }
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }
}
