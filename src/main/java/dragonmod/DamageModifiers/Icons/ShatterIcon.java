package dragonmod.DamageModifiers.Icons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import dragonmod.DragonMod;
import dragonmod.ui.TextureLoader;

public class ShatterIcon extends AbstractCustomIcon {
    public static final String ID = DragonMod.makeID("Shatter");
    private static ShatterIcon singleton;
    public ShatterIcon() {
        super(ID, TextureLoader.getTexture(DragonMod.uiPath("Icicle.png")));
    }

    public static ShatterIcon get()
    {
        if (singleton == null) {
            singleton = new ShatterIcon();
        }
        return singleton;
    }
}
