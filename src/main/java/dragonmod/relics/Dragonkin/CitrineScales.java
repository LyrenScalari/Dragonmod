package dragonmod.relics.Dragonkin;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.relics.BaseRelic;

public class CitrineScales extends BaseRelic {
    public static final String ID = DragonMod.makeID("CitrineScales");
    public static final String NAME = "CitrineScales";
    public CitrineScales() {
        super(ID, NAME, RelicTier.UNCOMMON, LandingSound.HEAVY);
        tips.clear();
        tips.add(new PowerTip(name, description));
        counter = 1;
    }

    @Override
    public boolean canSpawn() {return !AbstractDungeon.player.hasRelic(ObsidianScale.ID);}

    @Override
    public void onMonsterDeath(AbstractMonster m) {
    }
    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
