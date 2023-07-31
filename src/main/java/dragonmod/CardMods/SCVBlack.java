package dragonmod.CardMods;

import basemod.abstracts.AbstractCardModifier;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;


public class SCVBlack extends AbstractCardModifier {
    public SCVBlack () {}
    public void onSingleCardViewRender(AbstractCard card, SpriteBatch sb) {
/*        if (card instanceof AbstractDefaultCard){
            ExtraIcons.icon(BlackIcon.get().region.getTexture()).text(String.valueOf(((AbstractDefaultCard)card).energyCosts.get(TypeEnergyHelper.Mana.Red))).render(card);
        }*/
    }
    public AbstractCardModifier makeCopy() {
        return new SCVBlack();
    }
}