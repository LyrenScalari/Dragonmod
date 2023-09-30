package dragonmod.relics.Drifter;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.RelicStrings;
import dragonmod.util.StigmataManager;
import dragonmod.relics.BaseRelic;

import static dragonmod.DragonMod.makeID;

public class DraconicTimeCrystal  extends BaseRelic {
    public static final String ID = makeID(DraconicTimeCrystal.class.getSimpleName());
    public static final String NAME = "DraconicTimeCrystal";
    private static RelicStrings relicStrings = CardCrawlGame.languagePack.getRelicStrings(ID);
    public DraconicTimeCrystal () {
        super(ID, NAME, RelicTier.UNCOMMON, LandingSound.CLINK);
        description = relicStrings.DESCRIPTIONS[0];
        tips.clear();
        tips.add(new PowerTip(name, description));
        counter = 1;
    }

    public void onVictory() {
        this.flash();
        this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractPlayer p = AbstractDungeon.player;
        if (p.currentHealth > 0) {
            p.heal(StigmataManager.StigmataField.Stigmata.get(p)/2);
        }

    }
    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}