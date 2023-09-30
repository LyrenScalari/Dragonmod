package dragonmod.powers.Dragonkin;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DamageModifiers.HolyDamage;
import dragonmod.DragonMod;
import dragonmod.actions.CureAction;
import dragonmod.powers.BasePower;
import dragonmod.ui.SanctifyHaloEffect;
import dragonmod.util.Wiz;

public class SanctifyPower extends BasePower implements CloneablePowerInterface {
    public AbstractCreature source;
    public SanctifyHaloEffect halo;
    public static final String POWER_ID = DragonMod.makeID("Sanctify");

    public SanctifyPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(POWER_ID,PowerType.DEBUFF,false,owner,source, amount);
        updateDescription();
        halo = new SanctifyHaloEffect(owner);
        //AbstractDungeon.effectsQueue.add(halo);
    }
    public float atDamageReceive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if (DamageModifierManager.modifiers(card).stream().noneMatch(mod -> mod instanceof HolyDamage)){
            return damage * 0.75F;
        } else {
            return damage;
        }
    }
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.THORNS && DamageModifierManager.getDamageMods(info).stream().noneMatch(mod -> mod instanceof HolyDamage) &&
                info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner) {
            this.flash();
            Wiz.atb(new CureAction(amount));
            Wiz.atb(new ReducePowerAction(owner,owner,this,1));
        }

        return damageAmount;
    }
    public void onRemove() {
        halo.isDone = true;
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    @Override
    public AbstractPower makeCopy() {
        return new SanctifyPower(owner, source, amount);
    }

    public float[] _lightsOutGetXYRI() {
        return new float[] {owner.hb.cX, owner.hb.cY,(float)(owner.hb.width+(0.01*amount)), 0.05f * amount};
    }

    public Color[] _lightsOutGetColor() {
        return new Color[] {Color.GOLD.cpy()};
    }
}
