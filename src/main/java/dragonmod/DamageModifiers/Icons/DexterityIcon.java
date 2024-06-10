package dragonmod.DamageModifiers.Icons;

import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.TipHelper;
import dragonmod.DragonMod;
import dragonmod.ui.TextureLoader;

import java.util.ArrayList;
import java.util.List;

public class DexterityIcon extends AbstractCustomIcon {
    public static final String ID = DragonMod.makeID("Dexterity");
    private static DexterityIcon singleton;

    public DexterityIcon() {
        super(ID, TextureLoader.getTexture(DragonMod.uiPath("Dexterity.png")));
    }
    @Override
    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo> retVal = new ArrayList<>();
        retVal.add(new TooltipInfo("[dragonmod:DexterityIcon] " + TipHelper.capitalize(GameDictionary.DEXTERITY.NAMES[0]), GameDictionary.keywords.get(GameDictionary.DEXTERITY.NAMES[0])));
        return retVal;
    }
    public static DexterityIcon get()
    {
        if (singleton == null) {
            singleton = new DexterityIcon();
        }
        return singleton;
    }
}