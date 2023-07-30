package dragonmod.DamageModifiers.Icons;

import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import dragonmod.DragonMod;
import dragonmod.util.TextureLoader;

import java.util.ArrayList;
import java.util.List;

public class TemporalIcon extends AbstractCustomIcon {
    public static final String ID = DragonMod.makeID("Temporal");
    private static TemporalIcon singleton;
    private static final UIStrings StabilityTooltip = CardCrawlGame.languagePack.getUIString("theDragonkin:StabilityTooltip");
    public TemporalIcon() {
        super(ID, TextureLoader.getTexture(DragonMod.uiPath("Stagger.png")));
    }

    public static TemporalIcon get()
    {
        if (singleton == null) {
            singleton = new TemporalIcon();
        }
        return singleton;
    }
    public List<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> tips = new ArrayList<>();
        tips.add(new TooltipInfo(StabilityTooltip.TEXT[0],StabilityTooltip.TEXT[1]));
        return tips;
    }
}