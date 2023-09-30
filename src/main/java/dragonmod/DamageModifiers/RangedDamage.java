package dragonmod.DamageModifiers;

import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.LockOnPower;

public class RangedDamage extends AbstractDamageModifier {
    boolean inherent;
    String TipType;
    public RangedDamage(boolean isinherent){
        inherent = isinherent;
    }
    public boolean isInherent() {
        return inherent;
    }
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCreature target, AbstractCard card) {
        if (target != null) {
            if (target.hasPower(LockOnPower.POWER_ID) && type == DamageInfo.DamageType.NORMAL) {
                return damage * 1.5F;
            }
        }
        return damage;
    }
    @Override
    public AbstractDamageModifier makeCopy() {
        return new RangedDamage(inherent);
    }
    public int priority() {
        return 2;
    }
}
