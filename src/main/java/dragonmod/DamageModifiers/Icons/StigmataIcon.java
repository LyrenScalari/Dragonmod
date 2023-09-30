package dragonmod.DamageModifiers.Icons;

import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import dragonmod.DragonMod;
import dragonmod.ui.TextureLoader;

import java.util.ArrayList;
import java.util.List;

public class StigmataIcon extends AbstractCustomIcon {
    public static final String ID = DragonMod.makeID("Stigmata");
    private static StigmataIcon singleton;
    private static final UIStrings StabilityTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:StabilityTooltip");
    public StigmataIcon() {
        super(ID, TextureLoader.getTexture(DragonMod.uiPath("StigmataIcon.png")));
    }

    public static StigmataIcon get()
    {
        if (singleton == null) {
            singleton = new StigmataIcon();
        }
        return singleton;
    }
    public List<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> tips = new ArrayList<>();
        tips.add(new TooltipInfo(StabilityTooltip.TEXT[0],StabilityTooltip.TEXT[1]));
        return tips;
    }
}