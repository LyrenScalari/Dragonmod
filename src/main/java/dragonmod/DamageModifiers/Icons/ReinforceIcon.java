package dragonmod.DamageModifiers.Icons;

import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import dragonmod.DragonMod;
import dragonmod.ui.TextureLoader;

import java.util.ArrayList;

public class ReinforceIcon extends AbstractCustomIcon {
    public static final String ID = DragonMod.makeID("Reinforce");
    private static ReinforceIcon singleton;
    private static final UIStrings ReinforceTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:Reinforce");
    public ReinforceIcon() {
        super(ID, TextureLoader.getTexture(DragonMod.uiPath("Reinforce.png")));
    }
    public ArrayList<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> tips = new ArrayList<>();
        tips.add(new TooltipInfo(ReinforceTooltip.TEXT[0], ReinforceTooltip.TEXT[1]));
        return tips;
    }
    public static ReinforceIcon get()
    {
        if (singleton == null) {
            singleton = new ReinforceIcon();
        }
        return singleton;
    }
}
