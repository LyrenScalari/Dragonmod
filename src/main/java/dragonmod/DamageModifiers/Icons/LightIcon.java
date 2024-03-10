package dragonmod.DamageModifiers.Icons;

import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import dragonmod.DragonMod;
import dragonmod.ui.TextureLoader;

import java.util.ArrayList;
import java.util.List;

public class LightIcon extends AbstractCustomIcon {
    public static final String ID = DragonMod.makeID("Light");
    private static LightIcon singleton;
    private static final UIStrings DivineBlockTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:Light");
    public LightIcon() {
        super(ID,TextureLoader.getTexture(DragonMod.uiPath("Light.png")));
    }

    public static LightIcon get()
    {
        if (singleton == null) {
            singleton = new LightIcon();
        }
        return singleton;
    }
    public List<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> tips = new ArrayList<>();
        tips.add(new TooltipInfo(DivineBlockTooltip.TEXT[0], DivineBlockTooltip.TEXT[1]));
        return tips;
    }
}