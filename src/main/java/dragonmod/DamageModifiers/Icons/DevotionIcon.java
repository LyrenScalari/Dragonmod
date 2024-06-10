package dragonmod.DamageModifiers.Icons;

import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import dragonmod.DragonMod;
import dragonmod.ui.TextureLoader;

import java.util.ArrayList;

public class DevotionIcon extends AbstractCustomIcon {
    public static final String ID = DragonMod.makeID("Devotion");
    private static DevotionIcon singleton;
    private static final UIStrings holyTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:Devotion");

    public DevotionIcon() {
        super(ID, TextureLoader.getTexture(DragonMod.powerPath("Sanctify.png")));
    }

    public ArrayList<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> tips = new ArrayList<>();
        tips.add(new TooltipInfo(holyTooltip.TEXT[0], holyTooltip.TEXT[1]));
        return tips;
    }

    public static DevotionIcon get() {
        if (singleton == null) {
            singleton = new DevotionIcon();
        }
        return singleton;
    }
}