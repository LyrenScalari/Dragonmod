package dragonmod.interfaces;

import com.megacrit.cardcrawl.cards.DamageInfo;

public interface ReciveDamageinHandCard {
    int onReciveDamage(int damage, DamageInfo.DamageType type);
}
