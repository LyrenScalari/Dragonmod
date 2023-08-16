package dragonmod.powers.Rimedancer;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnLoseBlockPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import dragonmod.DragonMod;
import dragonmod.powers.BasePower;
import dragonmod.ui.ThrowIceDaggerEffect;
import dragonmod.util.Wiz;

public class IceSpikesPower extends BasePower implements CloneablePowerInterface, OnLoseBlockPower {
    public AbstractCreature source;
    public static final String POWER_ID = DragonMod.makeID("IceSpikes");
    private static AbstractPower powerToLose = new ThornsPower(null,0);
    public IceSpikesPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(POWER_ID,PowerType.BUFF,false,owner,source, amount);
        priority = 70;
        this.loadRegion("thorns");
        this.img = powerToLose.img;
        this.region48 = powerToLose.region48;
        this.region128 = powerToLose.region128;
        updateDescription();
    }
    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, Color.CYAN.cpy());
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    @Override
    public AbstractPower makeCopy() {
        return new IceSpikesPower(owner, source, amount);
    }

    @Override
    public int onLoseBlock(DamageInfo damageInfo, int i) {
        if (damageInfo.type == DamageInfo.DamageType.NORMAL) {
            if (damageInfo.owner.hasPower(Chillpower.POWER_ID)) {
                if (i < amount) {
                    AbstractDungeon.actionManager.addToTop(
                            new ReducePowerAction(this.owner, this.owner, this, Math.max(1, i / 2)));
                } else {
                    AbstractDungeon.actionManager.addToTop(
                            new ReducePowerAction(this.owner, this.owner, this, Math.max(1, amount / 2)));
                }
            }
            Wiz.vfx(new ThrowIceDaggerEffect(damageInfo.owner.hb.cX, damageInfo.owner.hb.cY, 5f));
            if (i < amount) {
                Wiz.dmg(damageInfo.owner, new DamageInfo(owner, i, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
            } else {
                Wiz.dmg(damageInfo.owner, new DamageInfo(owner, amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
            }
        }
        return 0;
    }
}