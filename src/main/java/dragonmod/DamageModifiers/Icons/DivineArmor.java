package dragonmod.DamageModifiers.Icons;

import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import dragonmod.DragonMod;
import dragonmod.ui.TextureLoader;

import java.util.ArrayList;
import java.util.List;

public class DivineArmor extends AbstractCustomIcon {
    public static final String ID = DragonMod.makeID("DivineArmor");
    private static DivineArmor singleton;
    private static final UIStrings DivineBlockTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:DivineArmor");
    public DivineArmor() {
        super(ID, TextureLoader.getTexture(DragonMod.uiPath("DivineArmor.png")));
    }

    public static DivineArmor get()
    {
        if (singleton == null) {
            singleton = new DivineArmor();
        }
        return singleton;
    }
    public List<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> tips = new ArrayList<>();
        tips.add(new TooltipInfo(DivineBlockTooltip.TEXT[0], DivineBlockTooltip.TEXT[1]));
        return tips;
    }
}