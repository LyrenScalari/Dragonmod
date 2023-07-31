package dragonmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dragonmod.patches.TemporalStressField;
import dragonmod.util.Wiz;


public class RestoreAction extends AbstractGameAction {
    int amt;
    public RestoreAction(int amount) {
        amt = amount;
    }

    @Override
    public void update() {
        if (this.duration == this.startDuration) {
                if (TemporalStressField.Stress.get(AbstractDungeon.player) >= amt) {
                    Wiz.atb(new GainStressAction(-amt));
                    Wiz.atb(new HealAction(AbstractDungeon.player,AbstractDungeon.player,amt));
                } else {
                    Wiz.atb(new GainStressAction(-TemporalStressField.Stress.get(AbstractDungeon.player)));
                    Wiz.atb(new HealAction(AbstractDungeon.player,AbstractDungeon.player,TemporalStressField.Stress.get(AbstractDungeon.player)));
                }
                isDone =true;
            }
    }
}
