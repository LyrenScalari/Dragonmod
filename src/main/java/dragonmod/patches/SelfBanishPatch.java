package dragonmod.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import dragonmod.DragonMod;
import dragonmod.interfaces.onExhaustedEnchantment;
import dragonmod.util.EnchantmentsField;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.Wiz;
import javassist.CtBehavior;

import static dragonmod.util.EnchantmentsManager.BanishedCards;

@SpirePatch2(
        clz = UseCardAction.class,
        method = "update"
)
public class SelfBanishPatch {
    @SpireInsertPatch(
            locator = Locator.class
    )
    public static SpireReturn Insert(UseCardAction __instance) {
        AbstractCard targetcard = ReflectionHacks.getPrivate(__instance,UseCardAction.class,"targetCard");
        if (targetcard.hasTag(DragonMod.Banish)) {
            __instance.isDone = true;
            AbstractDungeon.player.cardInUse = null;
            AbstractDungeon.effectsQueue.add(new PurgeCardEffect(targetcard));
            targetcard.triggerOnExhaust();
            for (AbstractCard enchantment : EnchantmentsField.Enchantments.get(Wiz.Player()).group) {
                if (enchantment instanceof onExhaustedEnchantment) {
                    Wiz.Player().limbo.group.add(enchantment);
                    Wiz.atb(new AbstractGameAction() {
                        @Override
                        public void update() {
                            EnchantmentsField.Enchantments.get(Wiz.Player()).group.remove(enchantment);
                            Wiz.atb(new UnlimboAction(enchantment));
                            ((onExhaustedEnchantment) enchantment).EnchantedOnExhaust(targetcard);
                            addToBot(new AbstractGameAction() {
                                @Override
                                public void update() {
                                    EnchantmentsManager.ActivateEnchantments(enchantment, Wiz.Player());
                                    isDone = true;
                                }
                            });
                            isDone = true;
                        }
                    });
                }
            }
            BanishedCards.addToBottom(targetcard);
            return SpireReturn.Return(null);
        } else {
            return SpireReturn.Continue();
        }
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractCard.class, "freeToPlayOnce");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}
