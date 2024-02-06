package dragonmod.DamageModifiers.Icons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import dragonmod.DragonMod;
import dragonmod.ui.TextureLoader;

public class ChargeCounter extends AbstractCustomIcon {
    public static final String ID = DragonMod.makeID("ChargeCounter");
    private static ChargeCounter singleton;
    public ChargeCounter() {
        super(ID, TextureLoader.getTexture(DragonMod.uiPath("ChargeCounters.png")));
    }
    public static ChargeCounter get() {
        if (singleton == null) {
            singleton = new ChargeCounter();
        }
        return singleton;
    }
}

