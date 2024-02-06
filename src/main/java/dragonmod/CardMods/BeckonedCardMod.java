package dragonmod.CardMods;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import dragonmod.util.Wiz;

public class BeckonedCardMod extends AbstractCardModifier {
    public String id;
    public BeckonedCardMod () {}
    public void onOtherCardPlayed(AbstractCard card, AbstractCard otherCard, CardGroup group) {
        if (group == Wiz.Player().hand){
            Wiz.atb(new DiscardSpecificCardAction(card,Wiz.Player().hand));
            Wiz.atb(new AbstractGameAction() {
                @Override
                public void update() {
                    CardModifierManager.removeSpecificModifier(card,BeckonedCardMod.this,true);
                    isDone = true;
                }
            });
        }
    }
    public void onUpdate(AbstractCard card) {
        if (card.glowColor != Color.SCARLET.cpy()){
            card.glowColor = Color.SCARLET.cpy();
        }
    }
    @Override
    public String identifier(AbstractCard card) {
        id = "BeckonedMod";
        return "BeckonedMod";
    }
    @Override
    public AbstractCardModifier makeCopy() {
        return new BeckonedCardMod();
    }
    public boolean shouldApply(AbstractCard card) {
        if (CardModifierManager.hasModifier(card,id)){
            return false;
        }else return true;
    }
}
