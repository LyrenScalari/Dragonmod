package dragonmod.DamageModifiers.Icons;

import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import dragonmod.DragonMod;
import dragonmod.ui.TextureLoader;

import java.util.ArrayList;
import java.util.List;

public class HexIcon extends AbstractCustomIcon {
    public static final String ID = DragonMod.makeID("Hex");
    private static HexIcon singleton;
    private static final UIStrings HexTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:Hex");
    private static final UIStrings DarkDamageTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:Dark");
    public HexIcon() {
        super(ID, TextureLoader.getTexture(DragonMod.uiPath("Hex.png")));
    }

    public static HexIcon get()
    {
        if (singleton == null) {
            singleton = new HexIcon();
        }
        return singleton;
    }
    public List<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> tips = new ArrayList<>();
        tips.add(new TooltipInfo(HexTooltip.TEXT[0], HexTooltip.TEXT[1]));
        tips.add(new TooltipInfo(DarkDamageTooltip.TEXT[0], DarkDamageTooltip.TEXT[1]));
        tips.add(new TooltipInfo("[dragonmod:WeakIcon] " + TipHelper.capitalize(GameDictionary.WEAK.NAMES[0]), GameDictionary.keywords.get(GameDictionary.WEAK.NAMES[0])));
        return tips;
    }
}
