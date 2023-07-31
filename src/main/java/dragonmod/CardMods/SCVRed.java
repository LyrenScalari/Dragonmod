package dragonmod.CardMods;

import basemod.abstracts.AbstractCardModifier;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;


public class SCVRed extends AbstractCardModifier {
    public SCVRed () {}
    public void onSingleCardViewRender(AbstractCard card, SpriteBatch sb) {
/*        if (card instanceof AbstractDragonCard){
            ExtraIcons.icon(RedIcon.get().region.getTexture()).text(String.valueOf(((AbstractDragonCard)card).energyCosts.get(TypeEnergyHelper.Mana.Red))).render(card);
        }*/
    }
    public AbstractCardModifier makeCopy() {
        return new SCVRed();
    }
}
