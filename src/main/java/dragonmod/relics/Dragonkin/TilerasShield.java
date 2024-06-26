package dragonmod.relics.Dragonkin;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import dragonmod.relics.BaseRelic;

import static dragonmod.DragonMod.makeID;


public class TilerasShield extends BaseRelic { // You must implement things you want to use from StSlib
    public static final String ID = makeID(TilerasShield.class.getSimpleName());
    // You can also have a relic be only usable once per combat. Check out Hubris for more examples, including other StSlib things.

    private boolean isPlayerTurn = false; // We should make sure the relic is only activateable during our turn, not the enemies'.
    private  boolean used = false;

    public TilerasShield() {
        super(ID, "placeholder_relic", RelicTier.RARE, LandingSound.HEAVY);
        tips.clear();
        tips.add(new PowerTip(name, description));
        counter = 0;
    }

    @Override
    public void onEquip() {
        AbstractDungeon.player.heal(AbstractDungeon.player.relics.size());
    }
    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
