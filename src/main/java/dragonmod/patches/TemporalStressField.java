package dragonmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;

@SpirePatch2(
        cls = "com.megacrit.cardcrawl.core.AbstractCreature",
        method = "<class>"
)
public class TemporalStressField {
    public static SpireField<Integer> Stress = new SpireField(() -> 0);
    public static SpireField<Integer> MaxStress = new SpireField(() -> 10);
    public TemporalStressField() {
    }
}
