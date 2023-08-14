package dragonmod.ui;

import actlikeit.RazIntent.CustomIntent;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import dragonmod.DragonMod;

public class BleedingOutIntent extends CustomIntent {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("dragonmod:BleedDeath");

    public BleedingOutIntent() {
        super(DragonMod.BLEEDING_OUT, uiStrings.TEXT[0], DragonMod.resourcePath("VFX/BleedOut_Above.png"),DragonMod.resourcePath("VFX/BleedOut_Tooltip.png"),uiStrings.TEXT[1]);
    }
}
