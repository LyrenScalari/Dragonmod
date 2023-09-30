package dragonmod.relics.Dragonkin;

import com.evacipated.cardcrawl.mod.stslib.actions.common.GainCustomBlockAction;
import com.evacipated.cardcrawl.mod.stslib.blockmods.BlockModContainer;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import dragonmod.DamageModifiers.BlockModifiers.DivineBlock;
import dragonmod.relics.BaseRelic;

import static dragonmod.DragonMod.makeID;

public class EmberCore extends BaseRelic {
    public static final String ID = makeID(EmberCore.class.getSimpleName());
    private boolean isPlayerTurn = false; // We should make sure the relic is only activateable during our turn, not the enemies'.
    private boolean used = false;
    public EmberCore() {
        super(ID, "EmberCore", RelicTier.COMMON, LandingSound.CLINK);
        tips.clear();
        tips.add(new PowerTip(name, description));
        counter = 4;
    }

    @Override
    public void onCardDraw(AbstractCard card){
        if (card.type == AbstractCard.CardType.STATUS && !used){
            addToBot(new DrawCardAction(1));
            addToBot(new GainCustomBlockAction(new BlockModContainer(this,new DivineBlock(true)),AbstractDungeon.player,counter));
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

