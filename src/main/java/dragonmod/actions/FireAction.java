package dragonmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import dragonmod.util.Wiz;

public class FireAction extends AbstractGameAction {
    private Class<? extends AbstractOrb> orbClass;
    public FireAction(Class<? extends AbstractOrb> orbClass) {
        super();
        this.orbClass = orbClass;
    }
    public FireAction() {}
    @Override
    public void update() {
        if (Wiz.adp().maxOrbs >= 1){
            if (orbClass != null){
                for (AbstractOrb o : Wiz.adp().orbs) {
                    if (orbClass.isInstance(o)) {
                        o.onStartOfTurn();
                        o.onEndOfTurn();
                        isDone = true;
                        return;
                    }
                }
            } else {
                Wiz.adp().orbs.get(0).onStartOfTurn();
                Wiz.adp().orbs.get(0).onEndOfTurn();
                isDone = true;
               }
        } else isDone = true;
    }
}