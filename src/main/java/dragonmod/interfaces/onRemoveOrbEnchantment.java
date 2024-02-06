package dragonmod.interfaces;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

public interface onRemoveOrbEnchantment {
    public boolean EnchantedOnRemoveOrb(AbstractCreature owner, AbstractOrb removedorb);
}
