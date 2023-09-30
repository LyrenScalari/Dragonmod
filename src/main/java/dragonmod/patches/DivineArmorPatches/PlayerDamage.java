package dragonmod.patches.DivineArmorPatches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.StrikeEffect;
import dragonmod.DragonMod;
import dragonmod.interfaces.ReciveDamageEffect;
import dragonmod.interfaces.ReciveDamageinHandCard;
import dragonmod.interfaces.ReciveModifyDamageEffect;
import dragonmod.interfaces.onAttackedField;
import dragonmod.util.*;
import dragonmod.patches.*;
import javassist.CtBehavior;

import java.util.ArrayList;

@SpirePatch(
        clz = AbstractPlayer.class,
        method = "damage"
)
public class PlayerDamage {
    static boolean hadDivineArmor;

    public PlayerDamage() {
    }

    @SpireInsertPatch(
            locator = Locator.class,
            localvars = {"damageAmount", "hadBlock"}
    )
    public static void Insert(AbstractCreature __instance, DamageInfo info,  int damageAmount, @ByRef boolean[] hadBlock) {
        if (!AbstractDungeon.getCurrRoom().isBattleOver) {
            if (__instance instanceof AbstractPlayer){
                DragonMod.damagetaken = true;
            }
            for (AbstractCard field : FieldsField.Fields.get(__instance)){
                if (field instanceof onAttackedField){
                    damageAmount = ((onAttackedField) field).AttachedOnAttacked(__instance,damageAmount, info);
                }
            }
            for (AbstractPower p : AbstractDungeon.player.powers) {
                if (p instanceof ReciveDamageEffect) {
                    ((ReciveDamageEffect) p).onReciveDamage(damageAmount);
                    ((ReciveDamageEffect) p).onReciveDamage(damageAmount);
                }
                if (p instanceof ReciveModifyDamageEffect) {
                    damageAmount = ((ReciveModifyDamageEffect) p).onReciveDamage(damageAmount,info);
                }
            }
            for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                if (c instanceof ReciveDamageEffect) {
                    ((ReciveDamageEffect) c).onReciveDamage(damageAmount);
                }
            }
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c instanceof ReciveDamageinHandCard) {
                    ((ReciveDamageinHandCard) c).onReciveDamage(damageAmount);
                }
                if (c instanceof ReciveDamageEffect) {
                    ((ReciveDamageEffect) c).onReciveDamage(damageAmount);
                }
            }
            for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
                if (c instanceof ReciveDamageEffect) {
                    ((ReciveDamageEffect) c).onReciveDamage(damageAmount);
                }
            }
            for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                if (c instanceof ReciveDamageEffect) {
                    ((ReciveDamageEffect) c).onReciveDamage(damageAmount);
                }
            }
            for (AbstractCard c : AbstractDungeon.player.limbo.group) {
                if (c instanceof ReciveDamageEffect) {
                    ((ReciveDamageEffect) c).onReciveDamage(damageAmount);
                }
            }
            if ((info.owner == AbstractDungeon.player || !AbstractDungeon.actionManager.turnHasEnded) ) {
                if (!HymnManager.ActiveVerses.isEmpty()){
                    ((ReciveModifyDamageEffect) HymnManager.ActiveVerses.get(0)).onReciveDamage(damageAmount, info);
                }
            }
        }
    }

    @SpireInsertPatch(
            locator = StrikeEffectLocator.class
    )
    public static SpireReturn<Void> Insert(AbstractCreature __instance, DamageInfo info) {
        if (hadDivineArmor) {
            return SpireReturn.Return();
        } else {
            return SpireReturn.Continue();
        }
    }

    private static class StrikeEffectLocator extends SpireInsertLocator {
        private StrikeEffectLocator() {
        }

        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.NewExprMatcher(StrikeEffect.class);
            int[] all = LineFinder.findAllInOrder(ctMethodToPatch, new ArrayList(), finalMatcher);
            return new int[]{all[all.length - 1]};
        }
    }

    private static class Locator extends SpireInsertLocator {
        private Locator() {
        }

        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "decrementBlock");
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
