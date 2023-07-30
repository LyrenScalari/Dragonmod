package dragonmod.patches;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dragonmod.util.TriggerOnCycleEffect;
import dragonmod.util.TriggerOnCycleMod;

@SpirePatch2(clz = AbstractCard.class, method = "triggerOnManualDiscard")
public class SealedPatch {
    @SpirePrefixPatch()
    public static void SealedPatch(AbstractCard __instance){
        if (AbstractDungeon.player.discardPile.getTopCard() instanceof AbstractPrimalCard ||AbstractDungeon.player.discardPile.getTopCard().type == AbstractCard.CardType.STATUS){
            for (AbstractCard ca : AbstractDungeon.player.discardPile.group){
                if (ca instanceof TriggerOnCycleEffect){
                    ((TriggerOnCycleEffect) ca).TriggerOnCycle(AbstractDungeon.player.discardPile.getTopCard());
                }
                for (AbstractCardModifier mod : CardModifierManager.modifiers(ca)) {
                    if (mod instanceof TriggerOnCycleMod) {
                        ((TriggerOnCycleMod) mod).TriggerOnCycle(ca, AbstractDungeon.player.discardPile.getTopCard());
                    }
                }
            }
            for (AbstractCard ca : AbstractDungeon.player.hand.group){
                if (ca instanceof TriggerOnCycleEffect){
                    ((TriggerOnCycleEffect) ca).TriggerOnCycle(AbstractDungeon.player.discardPile.getTopCard());
                }
                for (AbstractCardModifier mod : CardModifierManager.modifiers(ca)) {
                    if (mod instanceof TriggerOnCycleMod) {
                        ((TriggerOnCycleMod) mod).TriggerOnCycle(ca, AbstractDungeon.player.discardPile.getTopCard());
                    }
                }
            }
            for (AbstractCard ca : AbstractDungeon.player.drawPile.group){
                if (ca instanceof TriggerOnCycleEffect){
                    ((TriggerOnCycleEffect) ca).TriggerOnCycle(AbstractDungeon.player.discardPile.getTopCard());
                }
                for (AbstractCardModifier mod : CardModifierManager.modifiers(ca)) {
                    if (mod instanceof TriggerOnCycleMod) {
                        ((TriggerOnCycleMod) mod).TriggerOnCycle(ca, AbstractDungeon.player.discardPile.getTopCard());
                    }
                }
            }
            for (AbstractCard ca : AbstractDungeon.player.exhaustPile.group){
                if (ca instanceof TriggerOnCycleEffect){
                    ((TriggerOnCycleEffect) ca).TriggerOnCycle(AbstractDungeon.player.discardPile.getTopCard());
                }
                for (AbstractCardModifier mod : CardModifierManager.modifiers(ca)) {
                    if (mod instanceof TriggerOnCycleMod) {
                        ((TriggerOnCycleMod) mod).TriggerOnCycle(ca, AbstractDungeon.player.discardPile.getTopCard());
                    }
                }
            }
        }
    }
}
