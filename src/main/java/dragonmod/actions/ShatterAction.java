package dragonmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import dragonmod.orbs.Icicle;
import dragonmod.util.Wiz;

public class ShatterAction extends AbstractGameAction {
    public ShatterAction() {}
    @Override
    public void update() {
        if (this.duration == this.startDuration) {
            for (AbstractOrb o : Wiz.Player().orbs){
                if (o instanceof Icicle) {
                    Wiz.Player().orbs.remove(o);
                    Wiz.Player().orbs.add(0, o);
                    Wiz.atb(new AbstractGameAction() {
                        @Override
                        public void update() {
                            isDone = true;
                            Wiz.Player().removeNextOrb();
                        }
                    });
                    break;
                }
            }
        }
            this.tickDuration();
    }
}