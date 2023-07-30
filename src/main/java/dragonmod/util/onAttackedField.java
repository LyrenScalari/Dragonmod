package dragonmod.util;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public interface onAttackedField {

    public int AttachedOnAttacked(AbstractCreature owner, int dmgamt, DamageInfo info);
}
