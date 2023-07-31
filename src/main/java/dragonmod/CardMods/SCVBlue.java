package dragonmod.CardMods;

import basemod.abstracts.AbstractCardModifier;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.cards.AbstractCard;
import dragonmod.util.TypeEnergyHelper;

public class SCVBlue extends AbstractCardModifier {
    public SCVBlue () {}
    public void onSingleCardViewRender(AbstractCard card, SpriteBatch sb) {
        if (card instanceof AbstractDefaultCard){
            ExtraIcons.icon(BlueIcon.get().region.getTexture()).text(String.valueOf(((AbstractDefaultCard)card).energyCosts.get(TypeEnergyHelper.Mana.Blue))).render(card);
        }
    }
    public AbstractCardModifier makeCopy() {
        return new SCVBlue();
    }
}
