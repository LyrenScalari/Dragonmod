package dragonmod.CardMods;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import dragonmod.DragonMod;
import dragonmod.util.Wiz;

import static dragonmod.util.EnchantmentsManager.BanishedCards;

public class ConjuredCardMod extends AbstractCardModifier {
    public int duration;
    public ConjuredCardMod () {

    }
    public void onInitialApplication(AbstractCard card) {
        card.stopGlowing();
        card.unhover();
        card.unfadeOut();
        card.tags.add(DragonMod.Banish);
    }
    public void onExhausted(AbstractCard card) {
        AbstractDungeon.effectsQueue.add(new PurgeCardEffect(card));
        BanishedCards.addToBottom(card);
        Wiz.Player().exhaustPile.removeCard(card);
    }
    public String identifier(AbstractCard card) {
        return "ConjuredCardMod";
    }
    public boolean shouldApply(AbstractCard card) {
        if (CardModifierManager.hasModifier(card,"ConjuredCardMod")){
            return false;
        }
        return true;
    }
    @Override
    public AbstractCardModifier makeCopy() {
        return new ConjuredCardMod();
    }
}
