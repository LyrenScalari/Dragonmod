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
import dragonmod.DragonMod;
import dragonmod.powers.BasePower;
import dragonmod.util.Wiz;

public class RiposteDamage extends BasePower implements CloneablePowerInterface, OnMyBlockBrokenPower {
    public AbstractCreature source;
    public static final String POWER_ID = DragonMod.makeID("RiposteDamage");
    public AbstractMonster lastattacker;
    public RiposteDamage(final AbstractCreature owner, final AbstractCreature source, int amount) {
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
        sb.append(amount);
        sb.append(DESCRIPTIONS[1]);
        description = sb.toString();

    }


    public AbstractPower makeCopy() {
        return new RiposteDamage(owner, source,amount);
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner instanceof AbstractMonster && info.type != DamageInfo.DamageType.HP_LOSS) {
            lastattacker = (AbstractMonster) info.owner;
        }
        else {
            lastattacker = null;
        }
        return damageAmount;
    }


    public void onMyBlockBroken() {
        if (lastattacker != null){
            Wiz.dmg(lastattacker,new DamageInfo(owner,amount, DamageInfo.DamageType.THORNS));
            Wiz.atb(new RemoveSpecificPowerAction(owner,owner,this));
        }
    }
}