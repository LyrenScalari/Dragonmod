package dragonmod.relics.Dragonkin;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import dragonmod.DragonMod;
import dragonmod.cards.Justicar.AbstractPrimalCard;
import dragonmod.relics.BaseRelic;

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
    public void onManualDiscard() {
        if (AbstractDungeon.player.discardPile.getTopCard() instanceof AbstractPrimalCard ||AbstractDungeon.player.discardPile.getTopCard().type == AbstractCard.CardType.STATUS){
            this.flash();
            addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player, new VigorPower(AbstractDungeon.player,counter)));
            addToBot(new GainBlockAction(AbstractDungeon.player,counter));
        }
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
