package dragonmod.CardMods;

import basemod.abstracts.AbstractCardModifier;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.cards.AbstractCard;
import dragonmod.DamageModifiers.Icons.TemporalIcon;
import dragonmod.util.TypeEnergyHelper;

public class SCVTemporalCardMod extends AbstractCardModifier {
    public SCVTemporalCardMod () {}
    public void onSingleCardViewRender(AbstractCard card, SpriteBatch sb) {
        if (card instanceof AbstractDrifterCard){
            ExtraIcons.icon(TemporalIcon.get().region.getTexture()).text(String.valueOf(((AbstractDrifterCard)card).energyCosts.get(TypeEnergyHelper.Mana.Temporal))).render(card);
        }
    }
    public AbstractCardModifier makeCopy() {
        return new SCVTemporalCardMod();
    }
}