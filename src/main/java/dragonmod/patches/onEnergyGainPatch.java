package dragonmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.powers.Drifter.BorrowedTimeEnergy;

@SpirePatch2(clz = AbstractPlayer.class, method = "gainEnergy")
public class onEnergyGainPatch {
    @SpirePostfixPatch
    public static void OnDiscardPatch(AbstractPlayer __instance, int e) {
        for (AbstractPower p : AbstractDungeon.player.powers) {
            if (p instanceof BorrowedTimeEnergy) {
                ((BorrowedTimeEnergy) p).onEnergyGained(e);
            }
        }
    }
}
