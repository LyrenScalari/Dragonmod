package dragonmod.powers.Dragonkin;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DragonMod;
import dragonmod.actions.SmiteAction;
import dragonmod.powers.BaseTwoAmountPower;

public class PenancePower extends BaseTwoAmountPower implements CloneablePowerInterface, HealthBarRenderPower {
    public AbstractCreature source;
    public static final String POWER_ID = DragonMod.makeID("Penance");
    public static int Power = 20;
    public PenancePower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(POWER_ID,PowerType.DEBUFF,false,owner,source, amount);
        this.loadRegion("heartDef");
        updateDescription();
    }
    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, Color.GOLD.cpy());
    }
    @Override
    public void onInitialApplication() {
        amount2 = Power;
        updateDescription();
        if (this.amount >= 8){
            addToTop(new ReducePowerAction(owner,owner,this,8));
            CardCrawlGame.sound.play("POWER_MANTRA", 0.05F);
            addToBot(new SmiteAction(owner,new DamageInfo(owner,Power, DamageInfo.DamageType.HP_LOSS),false));
            Power += 10;
            updateDescription();
        }
    }
    @Override
    public void updateDescription() {
        amount2 = Power;
        description = DESCRIPTIONS[0] + 8 + DESCRIPTIONS[1] + Power + DESCRIPTIONS[2] + DESCRIPTIONS[3];
    }

    @Override
    public AbstractPower makeCopy() {
        return new PenancePower(owner, source, amount);
    }

    @Override
    public int getHealthBarAmount() {
        updateDescription();
        return Power;
    }
    public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
        FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(this.amount), x, y, this.fontScale, c);
    }
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if (this.amount >= 8){
            addToBot(new ReducePowerAction(owner,owner,this,8));
            CardCrawlGame.sound.play("POWER_MANTRA", 0.05F);
           addToBot(new SmiteAction(owner,new DamageInfo(owner,Power, DamageInfo.DamageType.HP_LOSS),false));
           Power += 10;
           updateDescription();
        }
    }

    @Override
    public Color getColor() {
        return Color.GOLD;
    }
}
