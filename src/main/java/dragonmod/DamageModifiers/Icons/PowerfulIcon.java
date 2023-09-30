package dragonmod.DamageModifiers.Icons;

import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import dragonmod.DragonMod;
import dragonmod.ui.TextureLoader;

import java.util.ArrayList;

public class PowerfulIcon extends AbstractCustomIcon {
    public static final String ID = DragonMod.makeID("Powerful");
    private static PowerfulIcon singleton;
    private static final UIStrings PowerfulTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:Powerful");
    public PowerfulIcon() {
        super(ID, TextureLoader.getTexture(DragonMod.uiPath("Powerful.png")));
    }
    public ArrayList<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> tips = new ArrayList<>();
        tips.add(new TooltipInfo(PowerfulTooltip.TEXT[0], PowerfulTooltip.TEXT[1]));
        return tips;
    }
    public static PowerfulIcon get()
    {
        if (singleton == null) {
            singleton = new PowerfulIcon();
        }
        return singleton;
    }
}