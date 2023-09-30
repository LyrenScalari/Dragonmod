package dragonmod.DamageModifiers.Icons;

import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import dragonmod.DragonMod;
import dragonmod.ui.TextureLoader;

import java.util.ArrayList;
import java.util.List;

public class FireIcon extends AbstractCustomIcon {
    public static final String ID = DragonMod.makeID("Fire");
    private static FireIcon singleton;
    private static final UIStrings FlameBarrierTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:FlameBarrier");
    public FireIcon() {
        super(ID, TextureLoader.getTexture(DragonMod.uiPath("FlameBarrier.png")));
    }

    public static FireIcon get()
    {
        if (singleton == null) {
            singleton = new FireIcon();
        }
        return singleton;
    }
    public List<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> tips = new ArrayList<>();
        tips.add(new TooltipInfo(FlameBarrierTooltip.TEXT[0], FlameBarrierTooltip.TEXT[1]));
        return tips;
    }
}