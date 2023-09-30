package dragonmod.DamageModifiers.Icons;

import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.TipHelper;
import dragonmod.DragonMod;
import dragonmod.ui.TextureLoader;

import java.util.ArrayList;
import java.util.List;

public class FocusIcon extends AbstractCustomIcon {
    public static final String ID = DragonMod.makeID("Focus");
    private static FocusIcon singleton;

    public FocusIcon() {
        super(ID, TextureLoader.getTexture(DragonMod.uiPath("Focus.png")));
    }
    @Override
    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo> retVal = new ArrayList<>();
        retVal.add(new TooltipInfo("[dragonmod:FocusIcon] " + TipHelper.capitalize(GameDictionary.FOCUS.NAMES[0]), GameDictionary.keywords.get(GameDictionary.FOCUS.NAMES[0])));
        return retVal;
    }
    public static FocusIcon get()
    {
        if (singleton == null) {
            singleton = new FocusIcon();
        }
        return singleton;
    }
}
