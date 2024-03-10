package dragonmod.DamageModifiers.Icons;

import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import dragonmod.DragonMod;
import dragonmod.ui.TextureLoader;

import java.util.ArrayList;
import java.util.List;

public class BanishIcon2 extends AbstractCustomIcon {
    public static final String ID = DragonMod.makeID("Banish2");
    private static BanishIcon2 singleton;
    private static final UIStrings LightDamageTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:Light");
    private static final UIStrings BanishTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:Banish");
    public BanishIcon2() {
        super(ID, TextureLoader.getTexture(DragonMod.uiPath("Banish.png")));
    }

    public static BanishIcon2 get()
    {
        if (singleton == null) {
            singleton = new BanishIcon2();
        }
        return singleton;
    }
    public List<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> tips = new ArrayList<>();
        tips.add(new TooltipInfo(LightDamageTooltip.TEXT[0], LightDamageTooltip.TEXT[1]));
        tips.add(new TooltipInfo(BanishTooltip.TEXT[0], BanishTooltip.TEXT[1]));
        return tips;
    }
}
