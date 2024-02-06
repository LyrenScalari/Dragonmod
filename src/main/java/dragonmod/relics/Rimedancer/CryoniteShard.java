package dragonmod.relics.Rimedancer;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import dragonmod.orbs.Icicle;
import dragonmod.powers.Rimedancer.Subzero;
import dragonmod.powers.Rimedancer.onRemoveOrbPower;
import dragonmod.relics.BaseRelic;
import dragonmod.util.Wiz;

import static dragonmod.DragonMod.makeID;

public class CryoniteShard extends BaseRelic implements onRemoveOrbPower {
    public static final String ID = makeID(CryoniteShard.class.getSimpleName());
    public static final UIStrings icicleStrings = CardCrawlGame.languagePack.getUIString(Icicle.ORB_ID);
    public static final UIStrings SubzeroStrings =  CardCrawlGame.languagePack.getUIString(makeID("Subzero"));
    public static boolean used = false;
    public CryoniteShard() {
        super(ID, "placeholder_relic", RelicTier.STARTER, LandingSound.HEAVY);
        tips.clear();
        counter = 4;
        tips.add(new PowerTip(name, description));
        tips.add(new PowerTip(icicleStrings.TEXT[0],icicleStrings.TEXT[1]));
        tips.add(new PowerTip(SubzeroStrings.TEXT[0],SubzeroStrings.TEXT[1]));
    }
    public void atPreBattle() {
        AbstractDungeon.player.channelOrb(new Icicle());
    }
    public void atBattleStartPreDraw() {
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }
    public void atTurnStartPostDraw() {
        used = false;
        grayscale = false;
    }
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onRemoveOrb(AbstractOrb orb) {
        if (!used){
            Wiz.applyToSelf(new Subzero(counter));
            used = true;
            grayscale = true;
        }
    }
}