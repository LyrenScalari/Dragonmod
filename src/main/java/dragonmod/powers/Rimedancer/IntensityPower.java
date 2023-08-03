package dragonmod.powers.Rimedancer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import dragonmod.DragonMod;
import dragonmod.powers.BasePower;
import dragonmod.util.Wiz;

public class IntensityPower extends BasePower {
    private AbstractPower powerToLose;
    public static final String POWER_ID = DragonMod.makeID("Intensity");
    public IntensityPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NeutralPowertypePatch.NEUTRAL, false, owner, amount);
        powerToLose = new FocusPower(owner,amount);
        this.img = powerToLose.img;
        this.region48 = powerToLose.region48;
        this.region128 = powerToLose.region128;
        updateDescription();
    }

    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, Color.PURPLE.cpy());
    }

    public void atStartOfTurnPostDraw() {
        flash();
        Wiz.applyToSelfTempstart(new FocusPower(owner,amount));
        addToBot(new RemoveSpecificPowerAction(owner, owner, this.ID));
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}