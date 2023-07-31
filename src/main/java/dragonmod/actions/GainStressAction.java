package dragonmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;

import static dragonmod.patches.TemporalStressField.*;

public class GainStressAction extends AbstractGameAction {
    AbstractCard src;
    public static boolean bonusdraw = false;
    public GainStressAction(AbstractCard card) {
        this.setValues(target, source, amount);
        this.actionType = ActionType.ENERGY;
        this.amount = ((AbstractDrifterCard)card).energyCosts.get(TypeEnergyHelper.Mana.Temporal);
        src = card;
    }
    public GainStressAction(int amount) {
        this.setValues(target, source, amount);
        this.actionType = ActionType.ENERGY;
        this.amount = amount;
    }
    public void update() {
        if (this.duration == 0.5F) {
            Stress.set(Wiz.adp(), Stress.get(Wiz.adp())+amount);
            if (Stress.get(Wiz.adp()) >= MaxStress.get(Wiz.adp()) && !bonusdraw){
                AbstractDungeon.player.gameHandSize += 1;
                bonusdraw = true;
            } else if (Stress.get(Wiz.adp()) < MaxStress.get(Wiz.adp()) && bonusdraw){
                AbstractDungeon.player.gameHandSize -= 1;
                bonusdraw = false;
            }
        }
        this.tickDuration();
    }
}