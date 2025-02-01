package dragonmod.relics.Dragonkin;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import dragonmod.interfaces.onExaltPower;
import dragonmod.relics.BaseRelic;
import dragonmod.util.Wiz;

import static dragonmod.DragonMod.makeID;

public class CitrineScales extends BaseRelic implements onExaltPower {
    public static final String ID = makeID(CitrineScales.class.getSimpleName());
    public static final String NAME = "CitrineScales";
    public CitrineScales() {
        super(ID, NAME, RelicTier.BOSS, LandingSound.HEAVY);
        tips.clear();
        tips.add(new PowerTip(name, description));
        counter = 1;
    }

    @Override
    public boolean canSpawn() {return !AbstractDungeon.player.hasRelic(ObsidianScales.ID);}

    @Override
    public void onMonsterDeath(AbstractMonster m) {
    }
    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void triggerOnExalt() {
        Wiz.applyToSelf(new EnergizedPower(Wiz.Player(),1));
    }
}
