package dragonmod.DamageModifiers.Icons;

import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import dragonmod.DragonMod;
import dragonmod.ui.TextureLoader;

import java.util.ArrayList;

public class ZealIcon extends AbstractCustomIcon {
    public static final String ID = DragonMod.makeID("Zeal");
    private static ZealIcon singleton;
    private static final UIStrings holyTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:Zeal");

    public ZealIcon() {
        super(ID, TextureLoader.getTexture(DragonMod.uiPath(("Zeal.png"))));
    }

    public ArrayList<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> tips = new ArrayList<>();
        tips.add(new TooltipInfo(holyTooltip.TEXT[0], holyTooltip.TEXT[1]));
        return tips;
    }

    public static ZealIcon get() {
        if (singleton == null) {
            singleton = new ZealIcon();
        }
        return singleton;
    }
}