package dragonmod.powers.Drifter;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DragonMod;
import dragonmod.powers.BasePower;

public class Decay extends BasePower implements CloneablePowerInterface {
    public AbstractCreature source;
    public static final String POWER_ID = DragonMod.makeID("Decay");
    public Decay(final AbstractCreature owner, final AbstractCreature source, int amount) {
        super(POWER_ID,PowerType.DEBUFF,false,owner,source, amount);
        this.loadRegion("cExplosion");
        updateDescription();
    }
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        addToBot(new DamageAction(owner,new DamageInfo(owner,amount, DamageInfo.DamageType.THORNS)));
        addToBot(new ReducePowerAction(owner,owner,this,1));
    }
    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, Color.SKY.cpy());
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new Decay(owner, source,amount);
    }

}
