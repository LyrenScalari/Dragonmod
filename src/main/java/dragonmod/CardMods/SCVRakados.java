package dragonmod.CardMods;

import basemod.abstracts.AbstractCardModifier;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;


public class SCVRakados extends AbstractCardModifier {
    public SCVRakados () {}
    public void onSingleCardViewRender(AbstractCard card, SpriteBatch sb) {
/*        if (card instanceof AbstractDragonCard){
            ExtraIcons.icon(RakadosIcon.get().region.getTexture()).text(String.valueOf(((AbstractDragonCard)card).energyCosts.get(TypeEnergyHelper.Mana.Rakados))).render(card);
        }*/
    }
    public AbstractCardModifier makeCopy() {
        return new SCVRakados();
    }
}