package dragonmod.DamageModifiers.Icons;

import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import dragonmod.DragonMod;
import dragonmod.ui.TextureLoader;

import java.util.ArrayList;
import java.util.List;

public class BanishIcon extends AbstractCustomIcon {
    public static final String ID = DragonMod.makeID("Banish");
    private static BanishIcon singleton;
    private static final UIStrings DivineBlockTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:Banish");
    public BanishIcon() {
        super(ID, TextureLoader.getTexture(DragonMod.uiPath("Banish.png")));
    }

    public static BanishIcon get()
    {
        if (singleton == null) {
            singleton = new BanishIcon();
        }
        return singleton;
    }
    public List<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> tips = new ArrayList<>();
        tips.add(new TooltipInfo(DivineBlockTooltip.TEXT[0], DivineBlockTooltip.TEXT[1]));
        return tips;
    }
}
