package dragonmod.relics.Dragonkin;

import com.evacipated.cardcrawl.mod.stslib.actions.common.GainCustomBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.RelicStrings;
import dragonmod.cards.Justicar.HolyWordBarrier;
import dragonmod.relics.BaseRelic;

import static dragonmod.DragonMod.makeID;

public class GarnetScales extends BaseRelic {
    public static final String ID = makeID(GarnetScales.class.getSimpleName());
    public static final String NAME = "GarnetScales";
    private static RelicStrings relicStrings = CardCrawlGame.languagePack.getRelicStrings(ID);

    public GarnetScales() {
        super(ID, NAME, RelicTier.STARTER, LandingSound.CLINK);
        description = relicStrings.DESCRIPTIONS[0];
        tips.clear();
        tips.add(new PowerTip(name, description));
        counter = 9;
    }

    @Override
    public void atPreBattle() {
        counter = 9;
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player,this));
        addToBot(new GainCustomBlockAction(new HolyWordBarrier(), AbstractDungeon.player,counter));
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
