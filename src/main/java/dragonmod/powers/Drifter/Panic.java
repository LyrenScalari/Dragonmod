package dragonmod.powers.Drifter;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DragonMod;
import dragonmod.powers.BaseTwoAmountPower;
import dragonmod.util.Wiz;

public class Panic extends BaseTwoAmountPower implements CloneablePowerInterface  {
    public static final String POWER_ID = DragonMod.makeID("Panic");
    public int reducamt = 0;
    public boolean looping = false;
    public Panic(final AbstractCreature owner, final AbstractCreature source, int amount) {
        super(POWER_ID,PowerType.DEBUFF,false,owner,source, amount);
        this.loadRegion("confusion");

        updateDescription();
    }
    public void atEndOfTurn(boolean isPlayer) {
        Wiz.atb(new ReducePowerAction(owner,owner,this,reducamt+1));
        reducamt = 0;
        looping = false;
    }
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (info.type == DamageInfo.DamageType.NORMAL && !looping){
            Wiz.att(new DamageRandomEnemyAction(new DamageInfo(info.owner,info.output, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.POISON));
            reducamt += 1;
            looping = true;
        } else {
            looping = false;
        }
    }
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL) {
            float mult = 1.0f-(amount*0.1f);
            if (mult < 0.5){
                mult = 0.5f;
            }
            return damage * mult;
        } else {
            return damage;
        }
    }
    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, Color.SCARLET.cpy());
    }

    @Override
    public void updateDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(DESCRIPTIONS[0]);
        if (amount >= 5){
            sb.append(50);
        } else {
            sb.append((int)((0.0+(amount*0.1))*100));
        }

        sb.append(DESCRIPTIONS[1]);
        description = sb.toString();
    }

    @Override
    public AbstractPower makeCopy() {
        return new Panic(owner, source,amount);
    }

}