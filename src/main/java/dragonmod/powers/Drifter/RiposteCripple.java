package dragonmod.powers.Drifter;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnMyBlockBrokenPower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import dragonmod.DragonMod;
import dragonmod.powers.BasePower;
import dragonmod.util.Wiz;

public class RiposteCripple extends BasePower implements CloneablePowerInterface, OnMyBlockBrokenPower {
    public AbstractCreature source;
    public static final String POWER_ID = DragonMod.makeID("RiposteCripple");
    public AbstractCreature lastattacker;
    public RiposteCripple(final AbstractCreature owner, final AbstractCreature source, int amount) {
        super(POWER_ID,PowerType.BUFF,false,owner,source, amount);
        this.loadRegion("flight");

        updateDescription();
    }

    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, Color.SKY.cpy());
    }


    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }


    public AbstractPower makeCopy() {
        return new RiposteCripple(owner, source,amount);
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        lastattacker = info.owner;
        return damageAmount;
    }


    public void onMyBlockBroken() {
        if (lastattacker != null){
            Wiz.applyToEnemy((AbstractMonster) lastattacker,new WeakPower(lastattacker,amount,false));
            Wiz.atb(new RemoveSpecificPowerAction(owner,owner,this));
        }
    }
}