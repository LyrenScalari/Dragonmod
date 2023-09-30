package dragonmod.DamageModifiers.Icons;

import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import dragonmod.DragonMod;
import dragonmod.ui.TextureLoader;


import java.util.ArrayList;
import java.util.List;

public class FrostIcon extends AbstractCustomIcon {
    public static final String ID = DragonMod.makeID("Frost");
    private static FrostIcon singleton;
    private static final UIStrings IceArmorTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:IceArmor");
    public FrostIcon() {
        super(ID, TextureLoader.getTexture(DragonMod.uiPath("IceArmor.png")));
    }

    public static FrostIcon get() {
        if (singleton == null) {
            singleton = new FrostIcon();
        }
        return singleton;
    }
    public List<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> tips = new ArrayList<>();
        tips.add(new TooltipInfo(IceArmorTooltip.TEXT[0],IceArmorTooltip.TEXT[1]));
        return tips;
    }
}

