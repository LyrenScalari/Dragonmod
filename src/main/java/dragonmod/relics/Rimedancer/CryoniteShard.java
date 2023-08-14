package dragonmod.relics.Rimedancer;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import dragonmod.orbs.Icicle;
import dragonmod.relics.BaseRelic;

import static dragonmod.DragonMod.makeID;

public class CryoniteShard extends BaseRelic {
    public static final String ID = makeID(CryoniteShard.class.getSimpleName());

    public CryoniteShard() {
        super(ID, "placeholder_relic", RelicTier.STARTER, LandingSound.HEAVY);
        tips.clear();
        tips.add(new PowerTip(name, description));
    }
    public void atPreBattle() {
        AbstractDungeon.player.channelOrb(new Icicle());
    }
    public void atBattleStartPreDraw() {
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new MakeTempCardInHandAction(new Shiv(), 1, false));
    }
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}