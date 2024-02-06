package dragonmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import dragonmod.orbs.Icicle;
import dragonmod.util.Wiz;

public class FireAction extends AbstractGameAction {
    private Class<? extends AbstractOrb> orbClass;
    public boolean allenemy;
    public FireAction(Class<? extends AbstractOrb> orbClass) {
        super();
        this.orbClass = orbClass;
    }
    public FireAction() {}
    public FireAction(boolean ALL,Class<? extends AbstractOrb> orbClass) {
        this(orbClass);
        allenemy = ALL;
    }
    @Override
    public void update() {
        if (Wiz.Player().maxOrbs >= 1){
            if (orbClass != null){
                for (AbstractOrb o : Wiz.Player().orbs) {
                    if (orbClass.isInstance(o)) {
                        if (!allenemy){
                            o.onStartOfTurn();
                            o.onEndOfTurn();
                            isDone = true;
                            return;
                        } else {
                            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters){
                                if (o instanceof Icicle && !m.isDeadOrEscaped()){
                                    ((Icicle)o).passiveEffect(m);
                                }
                            }
                        }
                    }
                    isDone = true;
                }
            } else {
                Wiz.Player().orbs.get(0).onStartOfTurn();
                Wiz.Player().orbs.get(0).onEndOfTurn();
                isDone = true;
               }
        } else isDone = true;
    }
}