package dragonmod.DamageModifiers.Icons;

import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.TipHelper;
import dragonmod.DragonMod;
import dragonmod.ui.TextureLoader;

import java.util.ArrayList;
import java.util.List;

public class StrengthIcon extends AbstractCustomIcon {
    public static final String ID = DragonMod.makeID("Strength");
    private static StrengthIcon singleton;

    public StrengthIcon() {
        super(ID, TextureLoader.getTexture(DragonMod.uiPath("Strength.png")));
    }
    @Override
    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo> retVal = new ArrayList<>();
        retVal.add(new TooltipInfo("[dragonmod:StrengthIcon] " + TipHelper.capitalize(GameDictionary.STRENGTH.NAMES[0]), GameDictionary.keywords.get(GameDictionary.STRENGTH.NAMES[0])));
        return retVal;
    }
    public static StrengthIcon get()
    {
        if (singleton == null) {
            singleton = new StrengthIcon();
        }
        return singleton;
    }
}
