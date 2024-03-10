package dragonmod.patches;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.BobEffect;
import dragonmod.DragonMod;
import dragonmod.orbs.SpecialOrbSlot;
import dragonmod.powers.Rimedancer.onRemoveOrbPower;
import dragonmod.ui.TextureLoader;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.Wiz;
import javassist.CtBehavior;

import java.util.EnumMap;

import static dragonmod.patches.CustomOrbSlotManager.SlotFields.SlotType;
import static dragonmod.patches.CustomOrbSlotManager.SlotFields.SpecialSlots;

public class CustomOrbSlotManager {
    @SpirePatch(
            cls = "com.megacrit.cardcrawl.orbs.AbstractOrb",
            method = "<class>"
    )
    public static class SlotFields{
        public static SpireField<SlotTypes> SlotType = new SpireField(() -> null);
        public static EnumMap<SlotTypes, SpecialOrbSlot> SpecialSlots = new EnumMap<>(SlotTypes.class);
        public enum SlotTypes{
            Crystal,
            Hail
        }
        public SlotFields(){
        }
    }

    @SpirePatch2(clz = AbstractPlayer.class, method = "channelOrb")
    public static class ChannelPatch{
        @SpireInsertPatch(
                locator= ChannelLocator.class,
                localvars = {"orbToSet","index"}
        )
        public static void onChannelOrb(AbstractPlayer __instance, AbstractOrb orbToSet,int index) {
            if (SlotType.get(__instance.orbs.get(index)) != null) {
                SlotType.set(orbToSet, SlotType.get(__instance.orbs.get(index)));
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
            RemoveOrb(__instance.orbs.get(0));
            if (SlotType.get(__instance.orbs.get(0)) != null) {
                return  SpecialSlots.get(SlotType.get(__instance.orbs.get(0))).ContainedOrbRemoved(__instance.orbs.get(0),false);
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
    public static void RemoveOrb(AbstractOrb orb){
        for (AbstractRelic r : Wiz.Player().relics){
            if (r instanceof onRemoveOrbPower){
                ((onRemoveOrbPower) r).onRemoveOrb(orb);
            }
        }
        for (AbstractPower p : Wiz.Player().powers){
            if (p instanceof onRemoveOrbPower){
                ((onRemoveOrbPower) p).onRemoveOrb(orb);
            }
        }
        EnchantmentsManager.RemoveOrbPlayer(orb);
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters){
            for (AbstractPower p : m.powers){
                if (p instanceof onRemoveOrbPower){
                    ((onRemoveOrbPower) p).onRemoveOrb(orb);
                }
            }
            if (!m.isDeadOrEscaped()){
                EnchantmentsManager.RemoveOrbMonster(orb,m);
            }
        }
    }

    @SpirePatch2(clz = AbstractPlayer.class, method = "removeNextOrb")
    public static class EvokePatch2{
        @SpirePrefixPatch
        public static SpireReturn<Void> onEvokeOrb(AbstractPlayer __instance) {
            if (!__instance.orbs.isEmpty() && !(__instance.orbs.get(0) instanceof EmptyOrbSlot)) {
                RemoveOrb(__instance.orbs.get(0));
                if (SlotType.get(__instance.orbs.get(0)) != null) {
                    return SpecialSlots.get(SlotType.get(__instance.orbs.get(0))).ContainedOrbRemoved(__instance.orbs.get(0),true);
                }
                return SpireReturn.Continue();
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch2(clz = AbstractPlayer.class, method = "applyStartOfTurnOrbs")
    public static class PassivePatch{
        @SpirePrefixPatch
        public static void applyStartOfTurnOrbs(AbstractPlayer __instance) {
            for (AbstractOrb o : __instance.orbs) {
                if ((SlotType.get(o) != null) && !(o instanceof EmptyOrbSlot)) {
                    SpecialSlots.get(SlotType.get(o)).ContainedOrbTurnStart(o);
                }
            }
        }
    }
    @SpirePatch2(clz = AbstractOrb.class, method = "onEndOfTurn")
    public static class EndTurnPatch{
        @SpirePrefixPatch
        public static void applyStartOfTurnOrbs(AbstractOrb __instance) {
            if ((SlotType.get(__instance) != null) && !(__instance instanceof EmptyOrbSlot)) {
                SpecialSlots.get(SlotType.get(__instance)).ContainedOrbTurnEnd(__instance);
            }
        }
    }
    @SpirePatch2(clz = AbstractOrb.class, method = "applyFocus")
    public static class ApplyFocusPatch{
        @SpirePrefixPatch
        public static void applyStartOfTurnOrbs(AbstractOrb __instance) {
            if ((SlotType.get(__instance) != null) && !(__instance instanceof EmptyOrbSlot)) {
                SpecialSlots.get(SlotType.get(__instance)).ContainedOrbApplyFocus(__instance);
            }
        }
    }
    @SpirePatch2(clz = AbstractOrb.class, method = "update")
    public static class SlotTipPatch {
        @SpirePostfixPatch
        public static void renderSlotTipPatch(AbstractOrb __instance) {
            if ((SlotType.get(__instance) != null) && !(__instance instanceof EmptyOrbSlot)) {
                if (__instance.hb.hovered){
                    SpecialSlots.get(SlotType.get(__instance)).SlotTip(__instance);
                }
            }
        }
    }
    @SpirePatch2(clz = AbstractPlayer.class, method = "render")
    public static class RenderCustomOrbSlots {
        public static float angle = MathUtils.random(360.0F);
        @SpirePrefixPatch
        public static void patch(AbstractPlayer __instance, SpriteBatch sb) {
            for (AbstractOrb orb : __instance.orbs){
                if (orb != null && !(orb instanceof EmptyOrbSlot)) {
                    if (SlotType.get(orb) != null) {
                        Texture Crystal;
                        if (SpecialSlots.get(SlotType.get(orb)).overlayimg != null){
                            Crystal = SpecialSlots.get(SlotType.get(orb)).overlayimg;
                        } else {
                            Crystal = TextureLoader.getTexture(DragonMod.orbPath("empty2.png"));
                        }
                        sb.setColor(Settings.CREAM_COLOR.cpy());
                        BobEffect bobEffect = new BobEffect(3.0F * Settings.scale, 3.0F);
                        angle += Gdx.graphics.getDeltaTime() * 10.0F;
                        sb.draw(Crystal, orb.cX - 48.0F + bobEffect.y / 8.0F, orb.cY - 48.0F - bobEffect.y / 8.0F, 48.0F, 48.0F, 96.0F, 96.0F, 1.0f, 1.0f, angle, 0, 0, 96, 96, false, false);
                    }
                }
            }
        }
    }
}
