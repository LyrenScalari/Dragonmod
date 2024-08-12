package dragonmod.DamageModifiers.Icons;

import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import dragonmod.DragonMod;
import dragonmod.ui.TextureLoader;

import java.util.ArrayList;
import java.util.List;

public class HexIcon2 extends AbstractCustomIcon {
    public static final String ID = DragonMod.makeID("Hex2");
    private static HexIcon2 singleton;
    private static final UIStrings HexTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:Hex");
    private static final UIStrings DarkDamageTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:Dark");
    public HexIcon2() {
        super(ID, TextureLoader.getTexture(DragonMod.uiPath("Hex.png")));
    }

    public static HexIcon2 get()
    {
        if (singleton == null) {
            singleton = new HexIcon2();
        }
        return singleton;
    }
    public List<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> tips = new ArrayList<>();
        tips.add(new TooltipInfo(HexTooltip.TEXT[0], HexTooltip.TEXT[1]));
        return tips;
    }
}
