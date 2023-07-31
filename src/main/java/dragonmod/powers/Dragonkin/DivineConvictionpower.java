package dragonmod.powers.Dragonkin;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DragonMod;
import dragonmod.cards.Justicar.AbstractHolyCard;
import dragonmod.powers.BasePower;

//Gain 1 dex for the turn for each card played.

public class DivineConvictionpower extends BasePower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = DragonMod.makeID("DivineConvictionpower");

    public DivineConvictionpower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(POWER_ID,PowerType.BUFF,false,owner,source, amount);
        this.loadRegion("mantra");
        updateDescription();
    }
    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, Color.GOLD.cpy());
    }
    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if (card instanceof AbstractHolyCard){
            damage = damage + this.amount;
        }
        return super.atDamageGive(damage, type, card);
    }

    @Override
    public float modifyBlock(float block, AbstractCard card) {
        if (card instanceof AbstractHolyCard){
            block = block + this.amount;
        }
        return super.modifyBlock(block, card);
    }
    @Override
    public void updateDescription() {
        if (amount < 5){
            description = DESCRIPTIONS[0] + amount;
        } else {
            description = DESCRIPTIONS[0] + amount;
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new DivineConvictionpower(owner, source, amount);
    }
}
