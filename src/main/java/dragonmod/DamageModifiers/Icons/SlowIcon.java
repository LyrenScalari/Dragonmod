package dragonmod.DamageModifiers.Icons;

import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import dragonmod.DragonMod;
import dragonmod.ui.TextureLoader;

import java.util.ArrayList;

public class SlowIcon extends AbstractCustomIcon {
    public static final String ID = DragonMod.makeID("Slow");
    private static SlowIcon singleton;
    private static final UIStrings holyTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:Slow");
    public SlowIcon() {
        super(ID, TextureLoader.getTexture(DragonMod.uiPath("Slow.png")));
    }
    public ArrayList<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> tips = new ArrayList<>();
        tips.add(new TooltipInfo(holyTooltip.TEXT[0], holyTooltip.TEXT[1]));
        return tips;
    }
    public static SlowIcon get()
    {
        if (singleton == null) {
            singleton = new SlowIcon();
        }
        return singleton;
    }
}
