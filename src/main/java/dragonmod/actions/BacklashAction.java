package dragonmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theDragonkin.patches.TemporalStressField;
import theDragonkin.util.TypeEnergyHelper;
import theDragonkin.util.Wiz;

import java.util.EnumMap;
import java.util.function.Supplier;

public class BacklashAction extends AbstractGameAction {
    int amt;
    Supplier<AbstractGameAction> BacklashEffect;
    public EnumMap<TypeEnergyHelper.Mana, Integer> BacklashCost;
    public BacklashAction(EnumMap<TypeEnergyHelper.Mana, Integer> backlashCost, Supplier<AbstractGameAction> backlashEffect) {
        amt = amount;
        BacklashEffect = backlashEffect;
        BacklashCost = backlashCost;
    }

    @Override
    public void update() {
        if (this.duration == this.startDuration) {
                if (TemporalStressField.Stress.get(AbstractDungeon.player) >= BacklashCost.get(TypeEnergyHelper.Mana.Temporal)) {
                    if (BacklashEffect != null) {
                        addToTop(BacklashEffect.get());
                    }
                    TemporalStressField.Stress.set(AbstractDungeon.player,TemporalStressField.Stress.get(AbstractDungeon.player)-BacklashCost.get(TypeEnergyHelper.Mana.Temporal));
                    addToBot(new HealAction(Wiz.adp(),Wiz.adp(),BacklashCost.get(TypeEnergyHelper.Mana.Temporal)));
                }

        }
        this.tickDuration();
    }
}