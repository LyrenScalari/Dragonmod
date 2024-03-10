package dragonmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import dragonmod.DragonMod;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Supplier;

import static dragonmod.util.EnchantmentsManager.BanishedCards;

public class BanishFromExhaustAction extends AbstractGameAction {
    int amt;
    int playcount;
    private AbstractPlayer p;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(DragonMod.makeID("BanishFromExhaustAction"));;
    public static final String[] TEXT = uiStrings.TEXT;
    private ArrayList<AbstractCard> exhumes = new ArrayList();
    public static ArrayList<AbstractCard> playcard = new ArrayList();
    Supplier<AbstractGameAction> ExileEffect;
    public BanishFromExhaustAction(int Amt, Supplier<AbstractGameAction> ExileEffect) {
        amt = Amt;
        this.p = AbstractDungeon.player;
        this.setValues(this.p, AbstractDungeon.player, this.amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.ExileEffect = ExileEffect;
    }

    @Override
    public void update() {
        AbstractCard derp;
        Iterator c;
        playcard.clear();
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.p.exhaustPile.isEmpty()) {
                this.isDone = true;
            } else if (this.p.exhaustPile.size() == 1) { {
                AbstractCard ca = this.p.exhaustPile.getTopCard();
                ca.unfadeOut();
                AbstractCard copy = ca.makeCopy();
                playcard.add(copy);
                this.p.exhaustPile.removeCard(ca);
                addToTop(ExileEffect.get());
                AbstractDungeon.effectsQueue.add(new PurgeCardEffect(ca));
                ca.triggerOnExhaust();
                BanishedCards.addToBottom(ca);
                ca.unhover();
                ca.fadingOut = false;
                this.isDone = true;
            }
            } else {
                c = this.p.exhaustPile.group.iterator();

                while(c.hasNext()) {
                    derp = (AbstractCard)c.next();
                    derp.stopGlowing();
                    derp.unhover();
                    derp.unfadeOut();
                }

                c = this.p.exhaustPile.group.iterator();

                while(c.hasNext()) {
                    derp = (AbstractCard)c.next();
                    this.exhumes.add(derp);
                }

                if (this.p.exhaustPile.isEmpty()) {
                    this.p.exhaustPile.group.addAll(this.exhumes);
                    this.exhumes.clear();
                    this.isDone = true;
                } else {
                    AbstractDungeon.gridSelectScreen.open(this.p.exhaustPile, amt, TEXT[0], false);
                    this.tickDuration();
                }
            }
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                for(c = AbstractDungeon.gridSelectScreen.selectedCards.iterator(); c.hasNext(); derp.unhover()) {
                    derp = (AbstractCard)c.next();
                    this.p.exhaustPile.removeCard(derp);
                    AbstractCard copy = derp.makeCopy();
                    playcard.add(copy);
                    addToTop(ExileEffect.get());
                    AbstractDungeon.effectsQueue.add(new PurgeCardEffect(derp));
                    derp.triggerOnExhaust();
                    BanishedCards.addToBottom(derp);
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                this.p.hand.refreshHandLayout();
                this.p.exhaustPile.group.addAll(this.exhumes);
                this.exhumes.clear();

                for(c = this.p.exhaustPile.group.iterator(); c.hasNext(); derp.target_y = 0.0F) {
                    derp = (AbstractCard)c.next();
                    derp.unhover();
                    derp.target_x = (float) CardGroup.DISCARD_PILE_X;
                }
            }

            this.tickDuration();
        }
    }
}
