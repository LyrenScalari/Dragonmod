package dragonmod.DamageModifiers.Icons;

import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import dragonmod.DragonMod;
import dragonmod.ui.TextureLoader;

import java.util.ArrayList;

public class CourageIcon extends AbstractCustomIcon {
    public static final String ID = DragonMod.makeID("Courage");
    private static CourageIcon singleton;
    private static final UIStrings CourageTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:Courage");
    public CourageIcon() {
        super(ID, TextureLoader.getTexture(DragonMod.uiPath("Courage.png")));
    }
    public ArrayList<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> tips = new ArrayList<>();
        tips.add(new TooltipInfo(CourageTooltip.TEXT[0], CourageTooltip.TEXT[1]));
        return tips;
    }
    public static CourageIcon get()
    {
        if (singleton == null) {
            singleton = new CourageIcon();
        }
        return singleton;
    }
}
