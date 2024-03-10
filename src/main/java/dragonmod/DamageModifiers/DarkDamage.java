package dragonmod.DamageModifiers;

import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.WeakPower;

public class DarkDamage extends AbstractDamageModifier {
    boolean inherent;
    String TipType;
    public DarkDamage(boolean isinherent){
        inherent = isinherent;
    }
    public boolean isInherent() {
        return inherent;
    }
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCreature target, AbstractCard card) {
        if (target != null) {
            if (target.hasPower(WeakPower.POWER_ID) && type != DamageInfo.DamageType.HP_LOSS) {
                return damage * 1.5F;
            }
        }
        return damage;
    }
    @Override
    public AbstractDamageModifier makeCopy() {
        return new DarkDamage(inherent);
    }
    public int priority() {
        return 2;
    }
}
