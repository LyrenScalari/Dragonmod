package dragonmod.util;

import com.megacrit.cardcrawl.cards.DamageInfo;

public interface ReciveModifyDamageEffect {
    int onReciveDamage(int damage, DamageInfo info);
}
