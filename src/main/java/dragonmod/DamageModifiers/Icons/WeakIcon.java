package dragonmod.DamageModifiers.Icons;

import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.TipHelper;
import dragonmod.DragonMod;
import dragonmod.ui.TextureLoader;

import java.util.ArrayList;
import java.util.List;

public class WeakIcon extends AbstractCustomIcon {
    public static final String ID = DragonMod.makeID("Weak");
    private static WeakIcon singleton;

    public WeakIcon() {
        super(ID, TextureLoader.getTexture(DragonMod.uiPath("Weak.png")));
    }
    @Override
    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo> retVal = new ArrayList<>();
        retVal.add(new TooltipInfo("[dragonmod:WeakIcon] " + TipHelper.capitalize(GameDictionary.WEAK.NAMES[0]), GameDictionary.keywords.get(GameDictionary.WEAK.NAMES[0])));
        return retVal;
    }
    public static WeakIcon get()
    {
        if (singleton == null) {
            singleton = new WeakIcon();
        }
        return singleton;
    }
}
