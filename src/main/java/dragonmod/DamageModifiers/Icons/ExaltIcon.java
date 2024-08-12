package dragonmod.DamageModifiers.Icons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import dragonmod.DragonMod;
import dragonmod.ui.TextureLoader;

public class ExaltIcon extends AbstractCustomIcon {
    public static final String ID = DragonMod.makeID("Exalt");
    private static ExaltIcon singleton;

    public ExaltIcon() {
        super(ID, TextureLoader.getTexture(DragonMod.uiPath("Inspire.png")));
    }

    public static ExaltIcon get()
    {
        if (singleton == null) {
            singleton = new ExaltIcon();
        }
        return singleton;
    }
}