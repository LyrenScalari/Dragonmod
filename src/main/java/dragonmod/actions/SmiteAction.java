package dragonmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dragonmod.ui.SmiteEffect;

import java.util.function.Supplier;

public class SmiteAction extends AbstractGameAction {
    private DamageInfo info;
    public boolean doDamage = false;
    private boolean shot = false;
    private boolean Redirect = true;
    public Supplier<AbstractGameAction> BonusEffect;
    public SmiteAction(AbstractCreature target, DamageInfo info, Supplier<AbstractGameAction> BonusEffect) {
        setValues(target, this.info = info);
        actionType = ActionType.DAMAGE;
        this.BonusEffect = BonusEffect;
    }
    public SmiteAction(AbstractCreature target, DamageInfo info) {
        setValues(target, this.info = info);
        actionType = ActionType.DAMAGE;
    }
    public SmiteAction(AbstractCreature target, DamageInfo info,boolean redirect) {
        setValues(target, this.info = info);
        actionType = ActionType.DAMAGE;
        Redirect = redirect;
    }
    @Override
    public void update() {
        if (shouldCancelAction()) {
            isDone = true;
            return;
        }
        if (!shot) {
            if (target.isDeadOrEscaped() && Redirect){
                target = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
            }
            AbstractDungeon.effectList.add(new SmiteEffect(target, this));
            shot = true;
        }
        if (doDamage && !target.isDeadOrEscaped()) {
            target.damage(info);
            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }
        if (doDamage || target.isDying) {
            isDone = true;
        }
    }
}