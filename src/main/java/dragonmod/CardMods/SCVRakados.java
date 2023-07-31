package dragonmod.CardMods;

import basemod.abstracts.AbstractCardModifier;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theDragonkin.DamageModifiers.Icons.RakadosIcon;
import theDragonkin.cards.AbstractDefaultCard;
import theDragonkin.util.TypeEnergyHelper;

public class SCVRakados extends AbstractCardModifier {
    public SCVRakados () {}
    public void onSingleCardViewRender(AbstractCard card, SpriteBatch sb) {
        if (card instanceof AbstractDefaultCard){
            ExtraIcons.icon(RakadosIcon.get().region.getTexture()).text(String.valueOf(((AbstractDefaultCard)card).energyCosts.get(TypeEnergyHelper.Mana.Rakados))).render(card);
        }
    }
    public AbstractCardModifier makeCopy() {
        return new SCVRakados();
    }
}