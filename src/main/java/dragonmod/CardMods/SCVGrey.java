package dragonmod.CardMods;

import basemod.abstracts.AbstractCardModifier;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class SCVGrey extends AbstractCardModifier {
    public SCVGrey () {}
    public void onSingleCardViewRender(AbstractCard card, SpriteBatch sb) {
/*        if (card instanceof AbstractDefaultCard){
            ExtraIcons.icon(GreyIcon.get().region.getTexture()).text(String.valueOf(((AbstractDefaultCard)card).energyCosts.get(TypeEnergyHelper.Mana.Colorless))).render(card);
        }*/
    }
    public AbstractCardModifier makeCopy() {
        return new SCVGrey();
    }
}