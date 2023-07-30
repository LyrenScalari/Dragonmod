package dragonmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;

@SpirePatch(
        cls = "com.megacrit.cardcrawl.core.AbstractCreature",
        method = "<class>"
)
public class TemporalStressField {
    public static SpireField<Integer> Stress = new SpireField(() -> {
        return 0;
    });
    public static SpireField<Integer> MaxStress = new SpireField(() -> {
        return 10;
    });
    public TemporalStressField() {
    }
}
