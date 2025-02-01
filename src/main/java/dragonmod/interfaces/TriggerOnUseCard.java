package dragonmod.interfaces;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public interface TriggerOnUseCard {
    void onUseCard(AbstractCard card, UseCardAction action);
}
