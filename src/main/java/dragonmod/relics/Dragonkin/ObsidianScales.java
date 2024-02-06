package dragonmod.relics.Dragonkin;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dragonmod.DragonMod;
import dragonmod.relics.BaseRelic;
import dragonmod.relics.Dragonkin.starter.GarnetScales;

import static dragonmod.DragonMod.makeID;

public class ObsidianScales extends BaseRelic {
    public static final String ID = makeID(ObsidianScales.class.getSimpleName());
    public static final String NAME = "ObsidianScales";
    public ObsidianScales() {
        super(ID, NAME, RelicTier.BOSS, LandingSound.HEAVY);
        counter = 4;
    }
    @Override
    public boolean canSpawn() {return AbstractDungeon.player.hasRelic(GarnetScales.ID) && !AbstractDungeon.player.hasRelic(CitrineScales.ID);}
    @Override
    public String getUpdatedDescription() {
        // Colorize the starter relic's name
        String name = new GarnetScales().name;
        StringBuilder sb = new StringBuilder();
        for (String word : name.split(" ")) {
            sb.append("[#").append(DragonMod.JUSTICAR_RED.cpy().toString()).append("]").append(word).append("[] ");
        }
        sb.setLength(sb.length()-1);
        sb.append("[#").append(DragonMod.JUSTICAR_RED.cpy().toString()).append("]");

        return DESCRIPTIONS[0] + sb.toString() + DESCRIPTIONS[1];
    }

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(GarnetScales.ID)) {
            for (int i=0; i<AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(GarnetScales.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }
    // Description

}
