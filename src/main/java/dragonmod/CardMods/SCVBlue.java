package dragonmod.CardMods;

import basemod.abstracts.AbstractCardModifier;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class SCVBlue extends AbstractCardModifier {
    public SCVBlue () {}
    public void onSingleCardViewRender(AbstractCard card, SpriteBatch sb) {
/*        if (card instanceof AbstractDefaultCard){
            ExtraIcons.icon(BlueIcon.get().region.getTexture()).text(String.valueOf(((AbstractDefaultCard)card).energyCosts.get(TypeEnergyHelper.Mana.Blue))).render(card);
        }*/
    }
    public AbstractCardModifier makeCopy() {
        return new SCVBlue();
    }
}
