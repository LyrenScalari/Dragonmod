package dragonmod.patches;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnMyBlockBrokenPower;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

@SpirePatch(
        clz = AbstractCreature.class,
        method = "brokeBlock"
)
public class OnMyBlockBrokenRelicPatch {
    public OnMyBlockBrokenRelicPatch() {
    }

    public static void Prefix(AbstractCreature __instance) {
        for (AbstractRelic r : AbstractDungeon.player.relics){
            if (r instanceof OnMyBlockBrokenPower) {
                ((OnMyBlockBrokenPower)r).onMyBlockBroken();
            }
        }
    }
}
