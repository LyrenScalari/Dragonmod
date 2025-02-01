package dragonmod.relics.Dragonkin.starter;

import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.actions.common.GainCustomBlockAction;
import com.evacipated.cardcrawl.mod.stslib.blockmods.BlockModContainer;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DamageModifiers.BlockModifiers.DivineBlock;
import dragonmod.actions.SmiteAction;
import dragonmod.powers.Dragonkin.InspirationPower;
import dragonmod.relics.BaseRelic;
import dragonmod.util.Wiz;

import static dragonmod.DragonMod.makeID;

public class GarnetScales extends BaseRelic {
    public static final String ID = makeID(GarnetScales.class.getSimpleName());
    public static final String NAME = "GarnetScales";
    private static RelicStrings relicStrings = CardCrawlGame.languagePack.getRelicStrings(ID);
    private static UIStrings barrier = CardCrawlGame.languagePack.getUIString("dragonmod:DivineArmor");
    private static UIStrings Inspiration = CardCrawlGame.languagePack.getUIString("dragonmod:Inspiration");
    public GarnetScales() {
        super(ID, NAME, RelicTier.STARTER, LandingSound.CLINK);
        description = relicStrings.DESCRIPTIONS[0];
        counter = 5;
        tips.clear();
        tips.add(new PowerTip(name, description));
        tips.add(new PowerTip(barrier.TEXT[0],barrier.TEXT[1]));
        tips.add(new PowerTip(Inspiration.TEXT[0], Inspiration.TEXT[1]));
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    public void atTurnStartPostDraw() {
        usedUp = false;
    }
    @Override
    public void atBattleStart() {
        this.flash();// 24
        this.addToBot(new DamageAction(Wiz.Player(),new DamageInfo(Wiz.Player(),counter, DamageInfo.DamageType.THORNS)));
        this.addToBot(new GainCustomBlockAction(new BlockModContainer(this,new DivineBlock(true)),Wiz.Player(),counter*2));
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }
    public int onPlayerGainedBlock(float blockAmount) {
        if (blockAmount > 0 && !usedUp){
            Wiz.atb(new RelicAboveCreatureAction(AbstractDungeon.player,this));
            Wiz.applyToSelf(new InspirationPower(Wiz.Player(),Wiz.Player(),1));
            AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
            Wiz.atb(new SmiteAction(target,new DamageInfo(AbstractDungeon.player,counter, DamageInfo.DamageType.THORNS)));
            usedUp = true;
        }
        return MathUtils.floor(blockAmount);
    }
}
