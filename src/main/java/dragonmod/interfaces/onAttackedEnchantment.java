package dragonmod.interfaces;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public interface onAttackedEnchantment {

    public int EnchantedOnAttacked(AbstractCreature owner, int dmgamt, DamageInfo info);
}
