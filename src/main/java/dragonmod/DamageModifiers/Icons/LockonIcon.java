package dragonmod.DamageModifiers.Icons;

import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.TipHelper;
import dragonmod.DragonMod;
import dragonmod.ui.TextureLoader;

import java.util.ArrayList;
import java.util.List;

public class LockonIcon extends AbstractCustomIcon {
    public static final String ID = DragonMod.makeID("Lockon");
    private static LockonIcon singleton;

    public LockonIcon() {
        super(ID, TextureLoader.getTexture(DragonMod.uiPath("Lock-On.png")));
    }
    @Override
    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo> retVal = new ArrayList<>();
        retVal.add(new TooltipInfo("[dragonmod:LockonIcon] " + TipHelper.capitalize(GameDictionary.LOCK_ON.NAMES[0]), GameDictionary.keywords.get(GameDictionary.LOCK_ON.NAMES[0])));
        return retVal;
    }
    public static LockonIcon get()
    {
        if (singleton == null) {
            singleton = new LockonIcon();
        }
        return singleton;
    }
}