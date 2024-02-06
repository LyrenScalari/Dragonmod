package dragonmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.TransformCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import dragonmod.cards.Warden.AbstractReflexiveCard;
import dragonmod.stances.DawnStance;
import dragonmod.stances.DuskStance;

import static dragonmod.util.Wiz.Player;
import static dragonmod.util.Wiz.att;
public class StanceChangePatch {
    @SpirePatch2(
            clz = AbstractPlayer.class,
            method = "onStanceChange"
    )
    public static class onChangeStance {
        @SpirePostfixPatch
        public static void StanceChangePostfix(AbstractPlayer __instance,String id) {
            for (int i = Player().hand.size()-1; i >= 0; i--) {
                AbstractCard c = Player().hand.group.get(i);
                if (!(c instanceof AbstractReflexiveCard)) continue;
                AbstractReflexiveCard newCard = swapThemOver((AbstractReflexiveCard) c,id);
                att(new TransformCardInHandAction(i, newCard));
            }
            for (int i = Player().drawPile.size()-1; i >= 0; i--) {
                AbstractCard c = Player().drawPile.group.get(i);
                if (!(c instanceof AbstractReflexiveCard)) continue;
                AbstractReflexiveCard newCard = swapThemOver((AbstractReflexiveCard) c,id);
                int index = Player().drawPile.group.indexOf(c);
                att(new AbstractGameAction() {
                    @Override
                    public void update() {
                        Player().drawPile.group.set(index, newCard);
                        this.isDone = true;
                    }
                });
            }
            for (int i = Player().discardPile.size()-1; i >= 0; i--) {
                AbstractCard c = Player().discardPile.group.get(i);
                if (!(c instanceof AbstractReflexiveCard)) continue;
                AbstractReflexiveCard newCard = swapThemOver((AbstractReflexiveCard) c,id);
                int index = Player().discardPile.group.indexOf(c);
                att(new AbstractGameAction() {
                    @Override
                    public void update() {
                        Player().discardPile.group.set(index, newCard);
                        this.isDone = true;
                    }
                });
            }
            for (int i = Player().exhaustPile.size()-1; i >= 0; i--) {
                AbstractCard c = Player().exhaustPile.group.get(i);
                if (!(c instanceof AbstractReflexiveCard)) continue;
                AbstractReflexiveCard newCard = swapThemOver((AbstractReflexiveCard) c,id);
                int index = Player().exhaustPile.group.indexOf(c);
                att(new AbstractGameAction() {
                    @Override
                    public void update() {
                        Player().exhaustPile.group.set(index, newCard);
                        this.isDone = true;
                    }
                });
            }
            for (int i = Player().limbo.size()-1; i >= 0; i--) {
                AbstractCard c = Player().limbo.group.get(i);
                if (!(c instanceof AbstractReflexiveCard)) continue;
                AbstractReflexiveCard newCard = swapThemOver((AbstractReflexiveCard) c,id);
                int index = Player().limbo.group.indexOf(c);
                att(new AbstractGameAction() {
                    @Override
                    public void update() {
                        Player().limbo.group.set(index, newCard);
                        this.isDone = true;
                    }
                });
            }
        }
    }
    static AbstractReflexiveCard swapThemOver(AbstractReflexiveCard input, String newStance) {
        AbstractReflexiveCard newCard;
        if (newStance.equals(DawnStance.STANCE_ID)){
            newCard = input.Amber;
            if (newCard.Amber == null) newCard.Amber = input;
            att(new AbstractGameAction() {
                @Override
                public void update() {
                    newCard.Amethyst.stopGlowing();
                    newCard.Amethyst.setAngle(0.0F, true);
                    newCard.Amethyst.lighten(true);
                    newCard.Amethyst.resetAttributes();
                    newCard.Emerald.stopGlowing();
                    newCard.Emerald.setAngle(0.0F, true);
                    newCard.Emerald.lighten(true);
                    newCard.Emerald.resetAttributes();
                    this.isDone = true;
                }
            });
        } else if (newStance.equals(DuskStance.STANCE_ID)) {
            newCard = input.Amethyst;
            if (newCard.Amethyst == null) newCard.Amethyst = input;
            att(new AbstractGameAction() {
                @Override
                public void update() {
                    newCard.Amber.stopGlowing();
                    newCard.Amber.setAngle(0.0F, true);
                    newCard.Amber.lighten(true);
                    newCard.Amber.resetAttributes();
                    newCard.Emerald.stopGlowing();
                    newCard.Emerald.setAngle(0.0F, true);
                    newCard.Emerald.lighten(true);
                    newCard.Emerald.resetAttributes();
                    this.isDone = true;
                }
            });
        } else {
            newCard = input.Emerald;
            System.out.println("Emerald Card :" + newCard.name);
            if (newCard.Emerald == null) newCard.Emerald = input;
            att(new AbstractGameAction() {
                @Override
                public void update() {
                    newCard.Amethyst.stopGlowing();
                    newCard.Amethyst.setAngle(0.0F, true);
                    newCard.Amethyst.lighten(true);
                    newCard.Amethyst.resetAttributes();
                    newCard.Amber.stopGlowing();
                    newCard.Amber.setAngle(0.0F, true);
                    newCard.Amber.lighten(true);
                    newCard.Amber.resetAttributes();
                    this.isDone = true;
                }
            });
        }
        return newCard;
    }
}
