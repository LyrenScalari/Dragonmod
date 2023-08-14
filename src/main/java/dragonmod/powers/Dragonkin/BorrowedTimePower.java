package dragonmod.powers.Dragonkin;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.TimeWarpPower;
import dragonmod.powers.BasePower;

import static dragonmod.DragonMod.makeID;

public class BorrowedTimePower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("BorrowedTime");
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public BorrowedTimePower(AbstractCreature owner, int amount) {
        super(POWER_ID,PowerType.DEBUFF,true,owner,owner, amount);
        this.loadRegion("time");
        powerToLose = new TimeWarpPower(owner);
        this.img = powerToLose.img;
        this.region48 = powerToLose.region48;
        this.region128 = powerToLose.region128;
        updateDescription();
    }

    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, Color.RED.cpy());
    }
    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+amount+DESCRIPTIONS[1];
    }
    public void atStartOfTurnPostDraw() {
        addToBot(new RemoveSpecificPowerAction(owner,owner,this));
    }
    public void onInitialApplication() {
        AbstractDungeon.player.gameHandSize -= this.amount;
    }
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        AbstractDungeon.player.gameHandSize -= this.amount;
    }
    public void onRemove() {
        AbstractDungeon.player.gameHandSize += this.amount;
    }
    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new BorrowedTimePower(owner, amount);
    }
}