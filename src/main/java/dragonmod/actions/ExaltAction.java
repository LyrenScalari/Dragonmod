package dragonmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import dragonmod.powers.Dragonkin.DivineConvictionpower;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.interfaces.onExaltPower;

import java.util.EnumMap;
import java.util.function.Supplier;

public class ExaltAction extends AbstractGameAction {
    Supplier<AbstractGameAction> ExaltEffect;
    public EnumMap<TypeEnergyHelper.Mana, Integer> ExaltCost;
    public ExaltAction(EnumMap<TypeEnergyHelper.Mana, Integer> exaltCost, Supplier<AbstractGameAction> exaltEffect) {
        ExaltEffect = exaltEffect;
        ExaltCost = exaltCost;
    }

    @Override
    public void update() {
        if (this.duration == this.startDuration) {
            if (AbstractDungeon.player.hasPower(DivineConvictionpower.POWER_ID)) {
                if (ExaltCost.get(TypeEnergyHelper.Mana.Exalt) <= AbstractDungeon.player.getPower(DivineConvictionpower.POWER_ID).amount) {
                    AbstractPower reduceMe = AbstractDungeon.player.getPower(DivineConvictionpower.POWER_ID);
                    if (reduceMe != null) {
                        for (AbstractPower p : AbstractDungeon.player.powers){
                            if (p instanceof onExaltPower){
                                ((onExaltPower) p).triggerOnExalt();
                            }
                        }
                        for (AbstractRelic p : AbstractDungeon.player.relics){
                            if (p instanceof onExaltPower){
                                ((onExaltPower) p).triggerOnExalt();
                            }
                        }
                        addToTop(new ReducePowerAction(AbstractDungeon.player,AbstractDungeon.player,reduceMe,ExaltCost.get(TypeEnergyHelper.Mana.Exalt)));
                        if (ExaltEffect != null){
                            addToTop(ExaltEffect.get());
                        }
                    }
                }
            }
        }
        this.tickDuration();
    }
}