package dragonmod.powers.Rimedancer;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DragonMod;
import dragonmod.powers.BasePower;
import dragonmod.ui.ThrowIceDaggerEffect;
import dragonmod.util.Wiz;

public class IceSpikesPower extends BasePower implements CloneablePowerInterface {
    public AbstractCreature source;
    public static final String POWER_ID = DragonMod.makeID("IceSpikes");
    public IceSpikesPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(POWER_ID,PowerType.DEBUFF,false,owner,source, amount);
        priority = 70;
        this.loadRegion("thorns");
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
            if (owner.currentBlock >= d){
                if (di.owner.hasPower(Chillpower.POWER_ID)){
                    AbstractDungeon.actionManager.addToTop(
                            new ReducePowerAction(this.owner, this.owner, this,amount/2));
                }  else {
                    AbstractDungeon.actionManager.addToTop(
                            new ReducePowerAction(this.owner, this.owner, this,amount));
                }

                Wiz.vfx(new ThrowIceDaggerEffect(di.owner.hb.cX,di.owner.hb.cY,5f));
                Wiz.dmg(di.owner,new DamageInfo(owner,amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
            } else {
                if (di.owner.hasPower(Chillpower.POWER_ID)){
                    AbstractDungeon.actionManager.addToTop(
                            new ReducePowerAction(this.owner, this.owner, this,owner.currentBlock/2));
                }  else {
                    AbstractDungeon.actionManager.addToTop(
                            new ReducePowerAction(this.owner, this.owner, this,owner.currentBlock));
                }
                Wiz.vfx(new ThrowIceDaggerEffect(di.owner.hb.cX,di.owner.hb.cY,5f));
                Wiz.dmg(di.owner,new DamageInfo(owner,owner.currentBlock, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
            }
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