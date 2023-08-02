package dragonmod.relics.Dragonkin;

import com.megacrit.cardcrawl.helpers.PowerTip;
import dragonmod.relics.BaseRelic;

import static dragonmod.DragonMod.makeID;

public class MukySludge extends BaseRelic {
    public static final String ID = makeID(MukySludge.class.getSimpleName());
    public static final String NAME = "PaperJragon";
    public MukySludge() {
        super(ID, NAME, RelicTier.UNCOMMON, LandingSound.MAGICAL);
        tips.clear();
        tips.add(new PowerTip(name, description));
    }
    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
