package dragonmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.orbs.CrystalOrbSlot;
import dragonmod.orbs.Icicle;
import dragonmod.patches.CustomOrbSlotManager;
import dragonmod.powers.Rimedancer.onRemoveOrbPower;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;

import java.util.Collections;
import java.util.EnumMap;
import java.util.function.Supplier;

public class ShatterAction extends AbstractGameAction {
    Supplier<AbstractGameAction> ShatterEffect;
    public EnumMap<TypeEnergyHelper.Mana, Integer> ShatterCost;

    public ShatterAction(EnumMap<TypeEnergyHelper.Mana, Integer> shatterCost, Supplier<AbstractGameAction> shatterEffect) {
        ShatterEffect = shatterEffect;
        ShatterCost = shatterCost;
    }

    @Override
    public void update() {
        if (this.duration == this.startDuration) {
            int icicles = 0;
            for (AbstractOrb o : Wiz.adp().orbs) {
                if (o instanceof Icicle) {
                    icicles++;
                }
            }

            if (icicles >= ShatterCost.get(TypeEnergyHelper.Mana.Shatter)) {
                int shattercount = 0;
                for (AbstractOrb o : Wiz.adp().orbs) {
                    if (o instanceof Icicle && shattercount < ShatterCost.get(TypeEnergyHelper.Mana.Shatter)) {
                        for (AbstractPower p : Wiz.adp().powers){
                            if (p instanceof onRemoveOrbPower){
                                ((onRemoveOrbPower) p).onRemoveOrb(Wiz.adp().orbs.get(0));
                            }
                        }
                        int i;
                        for(i = 1; i < Wiz.adp().orbs.size(); ++i) {
                            Collections.swap(Wiz.adp().orbs, i, i - 1);
                        }

                        if (CustomOrbSlotManager.SlotFields.Crystal.get(o)){
                            Wiz.adp().orbs.set(Wiz.adp().orbs.indexOf(o),new CrystalOrbSlot());
                        } else {
                            Wiz.adp().orbs.set(Wiz.adp().orbs.indexOf(o),new EmptyOrbSlot());
                        }

                        for(i = 0; i < Wiz.adp().orbs.size(); ++i) {
                            ((AbstractOrb)Wiz.adp().orbs.get(i)).setSlot(i,Wiz.adp().maxOrbs);
                        }
                        shattercount++;
                    }
                }
                addToTop(ShatterEffect.get());
            }
            this.tickDuration();
        }
    }
}