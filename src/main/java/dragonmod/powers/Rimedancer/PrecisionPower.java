package dragonmod.powers.Rimedancer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AccuracyPower;
import dragonmod.DragonMod;
import dragonmod.powers.BasePower;

public class PrecisionPower extends BasePower {
    private AbstractPower powerToLose;
    public static final String POWER_ID = DragonMod.makeID("Precision");
    @SpireEnum
    public static AbstractCard.CardTags Shiv;
    public PrecisionPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        updateDescription();
        this.loadRegion("accuracy");
        powerToLose = new AccuracyPower(owner,amount);
        this.img = powerToLose.img;
        this.region48 = powerToLose.region48;
        this.region128 = powerToLose.region128;
    }
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if (card.cost == 0){
            return type == DamageInfo.DamageType.NORMAL ? damage + (float)this.amount : damage;
        }
        return damage;
    }
    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, Color.PURPLE.cpy());
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
