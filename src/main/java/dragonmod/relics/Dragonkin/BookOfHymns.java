package dragonmod.relics.Dragonkin;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnMyBlockBrokenPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import dragonmod.DragonMod;
import dragonmod.powers.Dragonkin.DivineConvictionpower;
import dragonmod.relics.BaseRelic;

public class BookOfHymns extends BaseRelic implements OnMyBlockBrokenPower {
    public static final String ID = DragonMod.makeID("BookOfHymns");
    public static final String NAME = "BookOfHymns";
    public BookOfHymns() {
        super(ID, NAME, RelicTier.UNCOMMON, LandingSound.HEAVY);
        tips.clear();
        tips.add(new PowerTip(name, description));
    }
    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onMyBlockBroken() {
        if (!AbstractDungeon.actionManager.turnHasEnded){
            addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new DivineConvictionpower(AbstractDungeon.player,AbstractDungeon.player,1)));
        }
    }
}
