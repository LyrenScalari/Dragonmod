package dragonmod.patches;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import dragonmod.actions.ThrowIcicleAction;
import dragonmod.orbs.CrystalOrbSlot;
import dragonmod.orbs.HailOrbSlot;
import dragonmod.powers.Rimedancer.onRemoveOrbPower;
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
        public static SpireField<Boolean> Hail = new SpireField(() -> false);
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
            if (__instance.orbs.get(index) instanceof HailOrbSlot) {
                CustomOrbSlotManager.SlotFields.Hail.set(orbToSet,true);
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
                Wiz.applyToSelfTempstartTop(new FocusPower(AbstractDungeon.player,1));
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
            if (SlotFields.Hail.get(__instance.orbs.get(0))) {
                ((AbstractOrb)__instance.orbs.get(0)).onEvoke();
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
    @SpirePatch2(clz = AbstractPlayer.class, method = "removeNextOrb")
    public static class EvokePatch2{
        @SpirePrefixPatch
        public static SpireReturn<Void> onEvokeOrb(AbstractPlayer __instance) {
            if (!__instance.orbs.isEmpty() && !(__instance.orbs.get(0) instanceof EmptyOrbSlot)) {
                for (AbstractPower p : __instance.powers){
                    if (p instanceof onRemoveOrbPower){
                        ((onRemoveOrbPower) p).onRemoveOrb(__instance.orbs.get(0));
                    }
                }
                if (SlotFields.Crystal.get(__instance.orbs.get(0))) {
                    AbstractOrb CrystalOrb = __instance.orbs.get(0);
                    Wiz.applyToSelfTempstartTop(new FocusPower(AbstractDungeon.player,1));
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
                } else if (SlotFields.Hail.get(__instance.orbs.get(0))) {
                    int i;
                    for(i = 1; i < __instance.orbs.size(); ++i) {
                        Collections.swap(__instance.orbs, i, i - 1);
                    }
                    for(i = 0; i < __instance.orbs.size(); ++i) {
                        ((AbstractOrb)__instance.orbs.get(i)).setSlot(i, __instance.maxOrbs);
                    }
                    return SpireReturn.Return();
                } else return SpireReturn.Continue();
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch2(clz = AbstractPlayer.class, method = "applyStartOfTurnOrbs")
    public static class PassivePatch{
        @SpirePrefixPatch
        public static void applyStartOfTurnOrbs(AbstractPlayer __instance) {
            for (AbstractOrb o : __instance.orbs) {
                if (CustomOrbSlotManager.SlotFields.Hail.get(o) && !(o instanceof EmptyOrbSlot)) {
                    for (int i = 0; i < 2; i++) {
                        AbstractCreature m = AbstractDungeon.getRandomMonster();
                        if (m != null) {
                            DamageInfo info = new DamageInfo(AbstractDungeon.player, o.passiveAmount, DamageInfo.DamageType.THORNS);
                            info.output = AbstractOrb.applyLockOn(m, info.base);
                            Wiz.atb(new ThrowIcicleAction(o, m.hb, Color.CYAN));
                            Wiz.dmg(m, info, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
                        }
                    }
                }
            }
        }
    }
}
