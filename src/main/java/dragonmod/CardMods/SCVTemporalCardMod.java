package dragonmod.CardMods;

import basemod.abstracts.AbstractCardModifier;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.cards.AbstractCard;
import dragonmod.DamageModifiers.Icons.ChargeCounter;
import dragonmod.cards.AbstractDragonCard;
import dragonmod.util.TypeEnergyHelper;

public class SCVTemporalCardMod extends AbstractCardModifier {
    public SCVTemporalCardMod () {}
    public void onSingleCardViewRender(AbstractCard card, SpriteBatch sb) {
        if (card instanceof AbstractDragonCard){
            int displaynum = ((AbstractDragonCard)card).energyCosts.get(TypeEnergyHelper.Mana.Charge);
            if (displaynum < 0){
                ExtraIcons.icon(ChargeCounter.get().region.getTexture()).text("").render(card);
            } else {
                ExtraIcons.icon(ChargeCounter.get().region.getTexture()).text(String.valueOf(displaynum)).render(card);
            }

        }
    }
    public AbstractCardModifier makeCopy() {
        return new SCVTemporalCardMod();
    }
}