package dragonmod.CardMods;

import basemod.abstracts.AbstractCardModifier;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.cards.AbstractCard;
import dragonmod.DamageModifiers.Icons.ExaltIcon;
import dragonmod.cards.AbstractDragonCard;
import dragonmod.util.TypeEnergyHelper;

public class SCVExaltCardmod extends AbstractCardModifier {
    public SCVExaltCardmod () {}
    public void onSingleCardViewRender(AbstractCard card, SpriteBatch sb) {
        if (card instanceof AbstractDragonCard){
            ExtraIcons.icon(ExaltIcon.get().region.getTexture()).text(String.valueOf(((AbstractDragonCard)card).energyCosts.get(TypeEnergyHelper.Mana.Exalt))).render(card);
        }
    }
    public AbstractCardModifier makeCopy() {
        return new SCVExaltCardmod();
    }
}