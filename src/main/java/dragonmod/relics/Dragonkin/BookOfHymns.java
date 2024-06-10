package dragonmod.relics.Dragonkin;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnMyBlockBrokenPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import dragonmod.powers.Dragonkin.ZealPower;
import dragonmod.relics.BaseRelic;

import static dragonmod.DragonMod.makeID;

public class BookOfHymns extends BaseRelic implements OnMyBlockBrokenPower {
    public static final String ID = makeID(BookOfHymns.class.getSimpleName());
    public static final String NAME = "BookofHymns";
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
            addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new ZealPower(AbstractDungeon.player,AbstractDungeon.player,1)));
        }
    }
}
