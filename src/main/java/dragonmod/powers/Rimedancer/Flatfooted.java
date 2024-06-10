package dragonmod.powers.Rimedancer;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DragonMod;
import dragonmod.powers.BasePower;
import dragonmod.util.Wiz;

public class Flatfooted extends BasePower implements CloneablePowerInterface {
    public AbstractCreature source;
    public static final String POWER_ID = DragonMod.makeID("Flatfooted");
    public Flatfooted(final AbstractCreature owner, final int amount) {
        super(POWER_ID, AbstractPower.PowerType.DEBUFF,false,owner, Wiz.Player(), amount);
        priority = 70;
        updateDescription();
    }
    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, Color.CYAN.cpy());
    }
    public void onGainedBlock(float blockAmount) {
        if (blockAmount > 0.0F) {// 27
            this.flash();// 28
            this.addToBot(new DamageAction(owner,new DamageInfo(owner,amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.NONE));
        }
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    @Override
    public AbstractPower makeCopy() {
        return new Flatfooted(owner, amount);
    }
}
