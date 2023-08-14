package dragonmod.CardMods;

import basemod.abstracts.AbstractCardModifier;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.cards.AbstractCard;
import dragonmod.DamageModifiers.Icons.ShatterIcon;
import dragonmod.cards.AbstractDragonCard;
import dragonmod.util.TypeEnergyHelper;

public class SCVShatterMod extends AbstractCardModifier {
    public SCVShatterMod () {}
    public void onSingleCardViewRender(AbstractCard card, SpriteBatch sb) {
        if (card instanceof AbstractDragonCard){
            ExtraIcons.icon(ShatterIcon.get().region.getTexture()).text(String.valueOf(((AbstractDragonCard)card).energyCosts.get(TypeEnergyHelper.Mana.Shatter))).render(card);
        }
    }
    public AbstractCardModifier makeCopy() {
        return new SCVShatterMod();
    }
}