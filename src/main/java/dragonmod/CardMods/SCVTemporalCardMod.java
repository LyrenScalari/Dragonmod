package dragonmod.CardMods;

import basemod.abstracts.AbstractCardModifier;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.cards.AbstractCard;
import dragonmod.DamageModifiers.Icons.StigmataIcon;
import dragonmod.cards.Warden.AbstractWardenCard;
import dragonmod.util.TypeEnergyHelper;

public class SCVTemporalCardMod extends AbstractCardModifier {
    public SCVTemporalCardMod () {}
    public void onSingleCardViewRender(AbstractCard card, SpriteBatch sb) {
        if (card instanceof AbstractWardenCard){
            ExtraIcons.icon(StigmataIcon.get().region.getTexture()).text(String.valueOf(((AbstractWardenCard)card).energyCosts.get(TypeEnergyHelper.Mana.Temporal))).render(card);
        }
    }
    public AbstractCardModifier makeCopy() {
        return new SCVTemporalCardMod();
    }
}