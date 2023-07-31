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
import com.megacrit.cardcrawl.powers.EnergizedPower;
import dragonmod.DragonMod;
import dragonmod.powers.BasePower;
import dragonmod.util.Wiz;

public class RiposteEnergize extends BasePower implements CloneablePowerInterface, OnMyBlockBrokenPower {
    public AbstractCreature source;
    public static final String POWER_ID = DragonMod.makeID("RiposteEnergize");
    public AbstractMonster lastattacker;
    public RiposteEnergize(final AbstractCreature owner, final AbstractCreature source, int amount) {
        super(POWER_ID,PowerType.BUFF,false,owner,source, amount);
        this.loadRegion("flight");

        updateDescription();
    }

    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, Color.SKY.cpy());
    }


    public void updateDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(DESCRIPTIONS[0]);
        for (int i = 0; i < amount; i++){
            sb.append("[E] ");
        }
        sb.append(DESCRIPTIONS[1]);
        description = sb.toString();

    }


    public AbstractPower makeCopy() {
        return new RiposteDamage(owner, source,amount);
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        lastattacker = (AbstractMonster) info.owner;
        return damageAmount;
    }


    public void onMyBlockBroken() {
        if (lastattacker != null){
            Wiz.applyToSelf(new EnergizedPower(owner,amount));
            Wiz.atb(new RemoveSpecificPowerAction(owner,owner,this));
        }
    }
}