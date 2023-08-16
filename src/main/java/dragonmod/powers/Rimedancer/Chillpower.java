package dragonmod.powers.Rimedancer;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DragonMod;
import dragonmod.powers.BasePower;
import dragonmod.util.Wiz;


public class Chillpower extends BasePower implements CloneablePowerInterface {
    public AbstractCreature source;
    public static final String POWER_ID = DragonMod.makeID("Chill");
    public Chillpower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
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
            if (owner.currentBlock < d){
                AbstractDungeon.actionManager.addToTop(
                        new ReducePowerAction(this.owner, this.owner, this,1));
                Wiz.block(di.owner,amount);
            }
            return d;
        }
        return d;
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + DESCRIPTIONS[2];
    }
    @Override
    public AbstractPower makeCopy() {
        return new Chillpower(owner, source, amount);
    }
    public float[] _lightsOutGetXYRI() {
        return new float[] {owner.hb.cX, owner.hb.cY, (float) (owner.hb.width+(0.10*amount)), 0.15f * amount};
    }

    public Color[] _lightsOutGetColor() {
        return new Color[] {Color.CYAN.cpy()};
    }
}