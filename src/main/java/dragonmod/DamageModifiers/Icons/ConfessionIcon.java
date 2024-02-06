package dragonmod.DamageModifiers.Icons;

import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import dragonmod.DragonMod;
import dragonmod.ui.TextureLoader;

import java.util.ArrayList;

public class ConfessionIcon extends AbstractCustomIcon {
    public static final String ID = DragonMod.makeID("Confession");
    private static ConfessionIcon singleton;
    private static final UIStrings holyTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:Confession");

    public ConfessionIcon() {
        super(ID, TextureLoader.getTexture(DragonMod.powerPath("Confession.png")));
    }

    public ArrayList<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> tips = new ArrayList<>();
        tips.add(new TooltipInfo(holyTooltip.TEXT[0], holyTooltip.TEXT[1]));
        return tips;
    }

    public static ConfessionIcon get() {
        if (singleton == null) {
            singleton = new ConfessionIcon();
        }
        return singleton;
    }
}
