package dragonmod.relics.Dragonkin;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.powers.Dragonkin.Scorchpower;
import dragonmod.powers.general.PowerfulPower;
import dragonmod.relics.BaseRelic;
import dragonmod.relics.Dragonkin.starter.GarnetScales;
import dragonmod.util.Wiz;

import static dragonmod.DragonMod.makeID;

public class ObsidianScales extends BaseRelic {
    public static final String ID = makeID(ObsidianScales.class.getSimpleName());
    public static final String NAME = "ObsidianScales";
    private static UIStrings Powerful = CardCrawlGame.languagePack.getUIString("dragonmod:Powerful");
    private static UIStrings Scorch = CardCrawlGame.languagePack.getUIString("dragonmod:Scorch");
    public ObsidianScales() {
        super(ID, NAME, RelicTier.BOSS, LandingSound.HEAVY);
        counter = 3;
        tips.clear();
        tips.add(new PowerTip(name, description));
        tips.add(new PowerTip(Scorch.TEXT[0], Scorch.TEXT[1]));
        tips.add(new PowerTip(Powerful.TEXT[0],Powerful.TEXT[1]));
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
    public void atTurnStartPostDraw() {
        usedUp = false;
    }
    @Override
    public void atBattleStart() {
        this.flash();// 24
        Wiz.applyToSelf(new Scorchpower(Wiz.Player(),Wiz.Player(),10));
        Wiz.applyToSelf(new PowerfulPower(Wiz.Player(),Wiz.Player(),5));
        this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }
    public int onPlayerGainedBlock(float blockAmount) {
        if (blockAmount > 0 && !usedUp){
            Wiz.atb(new RelicAboveCreatureAction(AbstractDungeon.player,this));
            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                Wiz.applyToEnemy(m,new Scorchpower(m,Wiz.Player(),counter));
            }
            usedUp = true;
        }
        return MathUtils.floor(blockAmount);
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
