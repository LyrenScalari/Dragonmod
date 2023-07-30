package dragonmod.DamageModifiers.Icons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import dragonmod.DragonMod;
import dragonmod.util.TextureLoader;

public class FireIcon extends AbstractCustomIcon {
    public static final String ID = DragonMod.makeID("Fire");
    private static FireIcon singleton;

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
}