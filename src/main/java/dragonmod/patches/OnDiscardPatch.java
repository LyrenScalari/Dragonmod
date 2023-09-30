package dragonmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.interfaces.TriggerOnCycleEffect;


@SpirePatch2(clz = AbstractPlayer.class, method = "updateCardsOnDiscard")
public class OnDiscardPatch {
    @SpirePostfixPatch
    public static void OnDiscardPatch() {
        for (AbstractPower p : AbstractDungeon.player.powers) {
            if (p instanceof TriggerOnCycleEffect) {
                ((TriggerOnCycleEffect) p).TriggerOnCycle(null);
            }
        }
    }
}

