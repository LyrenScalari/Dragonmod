package dragonmod.DamageModifiers.Icons;

import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.TipHelper;
import dragonmod.DragonMod;
import dragonmod.ui.TextureLoader;

import java.util.ArrayList;
import java.util.List;

public class VulnerableIcon extends AbstractCustomIcon {
    public static final String ID = DragonMod.makeID("Vulnerable");
    private static VulnerableIcon singleton;

    public VulnerableIcon() {
        super(ID, TextureLoader.getTexture(DragonMod.uiPath("Vulnerable.png")));
    }
    @Override
    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo> retVal = new ArrayList<>();
        retVal.add(new TooltipInfo("[dragonmod:VulnerableIcon] " + TipHelper.capitalize(GameDictionary.VULNERABLE.NAMES[0]), GameDictionary.keywords.get(GameDictionary.VULNERABLE.NAMES[0])));
        return retVal;
    }
    public static VulnerableIcon get()
    {
        if (singleton == null) {
            singleton = new VulnerableIcon();
        }
        return singleton;
    }
}
