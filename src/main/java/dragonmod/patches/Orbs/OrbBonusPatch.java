package dragonmod.patches.Orbs;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.orbs.AbstractOrb;


@SpirePatch(
        cls = "com.megacrit.cardcrawl.orbs.AbstractOrb",
        method = "applyFocus"
)
public class OrbBonusPatch {
    @SpirePostfixPatch
    public static void OrbBonus(AbstractOrb __instance) {
    }
}
