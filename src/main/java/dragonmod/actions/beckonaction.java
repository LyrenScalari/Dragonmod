package dragonmod.actions;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dragonmod.CardMods.BeckonedCardMod;

import java.util.Iterator;

public class beckonaction extends AbstractGameAction {
    private AbstractPlayer p;

    public beckonaction(int amount) {
        this.p = AbstractDungeon.player;// 17
        this.setValues(this.p, this.p, amount);// 18
        this.actionType = ActionType.CARD_MANIPULATION;// 19
        this.duration = Settings.ACTION_DUR_MED;// 20
    }// 22

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {// 26
            if (this.p.drawPile.isEmpty()) {// 28
                this.isDone = true;// 29
                return;// 30
            }

            CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);// 33
            Iterator var2 = this.p.drawPile.group.iterator();// 34

            AbstractCard card;
            while(var2.hasNext()) {
                card = (AbstractCard)var2.next();
                tmp.addToRandomSpot(card);// 36
            }

            if (tmp.size() == 0) {// 40
                this.isDone = true;// 41
                return;// 42
            }

            for(int i = 0; i < this.amount; ++i) {// 45
                if (!tmp.isEmpty()) {// 46
                    tmp.shuffle();// 47
                    card = tmp.getBottomCard();// 48
                    tmp.removeCard(card);// 49
                    if (this.p.hand.size() == 10) {// 50
                        this.p.drawPile.moveToDiscardPile(card);// 51
                        this.p.createHandIsFullDialog();// 52
                    } else {
                        card.unhover();// 54
                        card.lighten(true);// 55
                        card.setAngle(0.0F);// 56
                        card.drawScale = 0.12F;// 57
                        card.targetDrawScale = 0.75F;// 58
                        card.current_x = CardGroup.DRAW_PILE_X;// 59
                        card.current_y = CardGroup.DRAW_PILE_Y;// 60
                        this.p.drawPile.removeCard(card);// 61
                        CardModifierManager.addModifier(card,new BeckonedCardMod());
                        AbstractDungeon.player.hand.addToTop(card);// 62
                        AbstractDungeon.player.hand.refreshHandLayout();// 63
                        AbstractDungeon.player.hand.applyPowers();// 64
                    }
                }
            }

            this.isDone = true;// 69
        }

        this.tickDuration();// 71
    }// 72
}
