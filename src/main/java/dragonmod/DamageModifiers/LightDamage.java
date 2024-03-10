package dragonmod.DamageModifiers;

import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import dragonmod.util.Wiz;

public class LightDamage extends AbstractDamageModifier {
    boolean inherent;
    String TipType;
    public LightDamage(boolean isinherent){
        inherent = isinherent;
    }
    public boolean isInherent() {
        return inherent;
    }
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCreature target, AbstractCard card) {
        return damage + (Wiz.Player().exhaustPile.size());
    }
    @Override
    public AbstractDamageModifier makeCopy() {
        return new LightDamage(inherent);
    }
    public int priority() {
        return 2;
    }
}
