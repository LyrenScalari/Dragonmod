package dragonmod.powers.Rimedancer;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DragonMod;
import dragonmod.actions.ThrowShurikenAction;
import dragonmod.powers.BasePower;
import dragonmod.util.Wiz;

public class IceSpikesPower extends BasePower implements CloneablePowerInterface {
    public AbstractCreature source;
    public static final String POWER_ID = DragonMod.makeID("IceSpikes");
    public IceSpikesPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(POWER_ID,PowerType.DEBUFF,false,owner,source, amount);
        priority = 70;
        updateDescription();
    }
    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, Color.CYAN.cpy());
    }
    @Override
    public int onAttacked(DamageInfo di, int d){
        if (di.type == DamageInfo.DamageType.NORMAL) {
            this.flash();
            Wiz.att(new ThrowShurikenAction("iceshuriken", 1, di.owner.hb, Color.GRAY.cpy(), false));
            Wiz.att(new DamageAction(di.owner,new DamageInfo(owner,amount, DamageInfo.DamageType.THORNS)));
            Wiz.att(new RemoveSpecificPowerAction(owner,owner,this));
            return d;
        }
        return d;
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    @Override
    public AbstractPower makeCopy() {
        return new IceSpikesPower(owner, source, amount);
    }
}