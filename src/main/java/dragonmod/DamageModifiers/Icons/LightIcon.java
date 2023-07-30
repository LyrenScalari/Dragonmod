package dragonmod.DamageModifiers.Icons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import dragonmod.DragonMod;
import dragonmod.util.TextureLoader;

public class LightIcon extends AbstractCustomIcon {
    public static final String ID = DragonMod.makeID("Light");
    private static LightIcon singleton;

    public LightIcon() {
        super(ID,TextureLoader.getTexture(DragonMod.uiPath("DivineArmor.png")));
    }

    public static LightIcon get()
    {
        if (singleton == null) {
            singleton = new LightIcon();
        }
        return singleton;
    }
}