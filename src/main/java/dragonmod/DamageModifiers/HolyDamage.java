package dragonmod.DamageModifiers;

import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import dragonmod.powers.Dragonkin.SanctifyPower;

public class HolyDamage extends AbstractDamageModifier {
    boolean inherent;
    String TipType;
    public HolyDamage(boolean isinherent){
        inherent = isinherent;
    }
    public boolean isInherent() {
        return inherent;
    }
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCreature target, AbstractCard card) {
        if (target !=null){
            if (target.hasPower(SanctifyPower.POWER_ID)) {
                return damage * 1.25F;
            }
        }
        return damage;
    }
    @Override
    public AbstractDamageModifier makeCopy() {
        return new HolyDamage(inherent);
    }
    public int priority() {
        return 2;
    }
}
