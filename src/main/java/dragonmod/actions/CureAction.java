package dragonmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import dragonmod.util.StigmataManager;


public class CureAction extends AbstractGameAction {
    int amt;
    public CureAction(int amount) {
        amt = amount;
    }

    @Override
    public void update() {
        if (this.duration == this.startDuration) {
            StigmataManager.cureHealing(amt);
            isDone = true;
        }
    }
}
