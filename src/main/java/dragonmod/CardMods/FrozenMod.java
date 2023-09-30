package dragonmod.CardMods;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import dragonmod.DamageModifiers.Icons.IceCounter;
import dragonmod.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public class FrozenMod extends AbstractCardModifier {
    public int duration;
    private UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("dragonmod:FrozenMod");
    private UIStrings keyword = CardCrawlGame.languagePack.getUIString("dragonmod:Frozen");
    public FrozenMod () {
        duration = 3;
    }
    public void onRender(AbstractCard card, SpriteBatch sb) {
        ExtraIcons.icon(IceCounter.get().region.getTexture()).text(String.valueOf(duration)).render(card);
    }
    public void onInitialApplication(AbstractCard card) {
        card.selfRetain = true;
    }
    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return uiStrings.TEXT[0] + rawDescription;
    }
    public void onSingleCardViewRender(AbstractCard card, SpriteBatch sb) {
        ExtraIcons.icon(IceCounter.get().region.getTexture()).text(String.valueOf(duration)).render(card);
    }
    public void onRetained(AbstractCard card) {
        duration -= 1;
        if (duration <= 0){
            Wiz.atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    if (FrozenMod.this.duration <= 0){
                        CardModifierManager.removeSpecificModifier(card, FrozenMod.this,true);
                        card.selfRetain = false;
                    }
                }
            });
        }
    }
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
       duration -= 1;
        if (duration <= 0){
            Wiz.atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    if (FrozenMod.this.duration <= 0){
                        CardModifierManager.removeSpecificModifier(card, FrozenMod.this,true);
                        card.selfRetain = false;
                    }
                }
            });
        }
    }
    public String identifier(AbstractCard card) {
        return "FrozenMod";
    }
    public List<TooltipInfo> additionalTooltips(AbstractCard card) {
        ArrayList<TooltipInfo> retval = new ArrayList<TooltipInfo>();
        retval.add(new TooltipInfo(keyword.TEXT[0],keyword.TEXT[1]));
        return retval;
    }
    public boolean shouldApply(AbstractCard card) {
        if (CardModifierManager.hasModifier(card,"FrozenMod")){
            return false;
        }
        return true;
    }
    @Override
    public AbstractCardModifier makeCopy() {
        return new FrozenMod();
    }
}