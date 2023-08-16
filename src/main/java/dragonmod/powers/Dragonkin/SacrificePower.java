package dragonmod.powers.Dragonkin;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.SadisticPower;
import dragonmod.DragonMod;
import dragonmod.powers.BaseTwoAmountPower;

public class SacrificePower extends BaseTwoAmountPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = DragonMod.makeID("SacrificePower");
    public SacrificePower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(POWER_ID,NeutralPowertypePatch.NEUTRAL,false,owner,source, amount);
        this.loadRegion("sadistic");
        powerToLose = new SadisticPower(owner,amount);
        amount2 = 5;
        this.img = powerToLose.img;
        this.region48 = powerToLose.region48;
        this.region128 = powerToLose.region128;
        updateDescription();
    }
    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, Color.CYAN.cpy());
    }
    // note: If you want to apply an effect when a power is being applied you have 3 options:
    //onInitialApplication is "When THIS power is first applied for the very first time only."
    //onApplyPower is "When the owner applies a power to something else (only used by Sadistic Nature)."
    //onReceivePowerPower from StSlib is "When any (including this) power is applied to the owner."


    // At the end of the turn, remove gained Dexterity

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount2 + DESCRIPTIONS[1];
    }
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if (this.amount >= amount2){
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DivineConvictionpower(AbstractDungeon.player, AbstractDungeon.player, 1)));
            addToBot(new ReducePowerAction(owner,owner,this,amount2));
        }
    }
    @Override
    public void onInitialApplication() {
        updateDescription();
        if (this.amount >= amount2){
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DivineConvictionpower(AbstractDungeon.player, AbstractDungeon.player, 1)));
            addToBot(new ReducePowerAction(owner,owner,this,amount2));
        }
    }
    @Override
    public AbstractPower makeCopy() {
        return new DivineConvictionpower(owner, source, amount);
    }
}