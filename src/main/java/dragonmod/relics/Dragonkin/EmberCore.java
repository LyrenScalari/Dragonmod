package dragonmod.relics.Dragonkin;

import com.evacipated.cardcrawl.mod.stslib.actions.common.GainCustomBlockAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import dragonmod.DragonMod;
import dragonmod.cards.Justicar.HolyWordBarrier;
import dragonmod.relics.BaseRelic;

public class EmberCore extends BaseRelic { // You must implement things you want to use from StSlib
    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     * StSLib for Clickable Relics
     *
     * Usable once per turn. Right click: Evoke your rightmost orb.
     */

    // ID, images, text.
    public static final String ID = DragonMod.makeID("EmberCore");
    private boolean isPlayerTurn = false; // We should make sure the relic is only activateable during our turn, not the enemies'.
    private boolean used = false;
    public EmberCore() {
        super(ID, "EmberCore", RelicTier.UNCOMMON, LandingSound.CLINK);
        tips.clear();
        tips.add(new PowerTip(name, description));
        counter = 4;
    }

    @Override
    public void onCardDraw(AbstractCard card){
        AbstractCard DivineArmorRef = new HolyWordBarrier();
        if (card.type == AbstractCard.CardType.STATUS && !used){
            addToBot(new DrawCardAction(1));
            addToBot(new GainCustomBlockAction(DivineArmorRef,AbstractDungeon.player,counter));
            used = true;
        }
    }
    @Override
    public void atTurnStart(){
        used = false;
    }

    @Override
    public void onPlayerEndTurn() {
        used = false;
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}

