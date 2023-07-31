package dragonmod.relics.Drifter;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.RelicStrings;
import dragonmod.DragonMod;
import dragonmod.cards.Warden.TimeTurner;
import dragonmod.relics.BaseRelic;
import dragonmod.relics.onScryRelic;

public class BronzePocketWatch extends BaseRelic implements onScryRelic { // You must implement things you want to use from StSlib

    public static final String ID = DragonMod.makeID("BronzePocketWatch");

    private static RelicStrings relicStrings = CardCrawlGame.languagePack.getRelicStrings(ID);
    private boolean isPlayerTurn = false; // We should make sure the relic is only activateable during our turn, not the enemies'.
    private boolean used = false;
    public int Statuscount = 0;
    public int burncount = 0;
    public BronzePocketWatch () {
        super(ID,"BronzePocketWatch", RelicTier.STARTER, LandingSound.CLINK);
        description = relicStrings.DESCRIPTIONS[0];
        tips.clear();
        tips.add(new PowerTip(name, description));
        counter = 1;
    }

    public void atBattleStartPreDraw() {
        used = false;
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new MakeTempCardInHandAction(new TimeTurner(), 1, false));
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onScry() {
        if (!used){
            addToBot(new DrawCardAction(1));
            used = true;
        }
    }
}