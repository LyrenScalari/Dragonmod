package dragonmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import dragonmod.orbs.Icicle;
import dragonmod.patches.CustomOrbSlotManager;
import dragonmod.powers.Rimedancer.onRemoveOrbPower;
import dragonmod.util.Wiz;

import java.util.Collections;

public class ShatterAction extends AbstractGameAction {
    public ShatterAction() {}
    @Override
    public void update() {
        if (this.duration == this.startDuration) {
                for (AbstractOrb o : Wiz.adp().orbs) {
                    if (o instanceof Icicle) {
                        for (AbstractPower p : Wiz.adp().powers) {
                            if (p instanceof onRemoveOrbPower) {
                                ((onRemoveOrbPower) p).onRemoveOrb(Wiz.adp().orbs.get(0));
                            }
                        }
                        int i;
                        for (i = 1; i < Wiz.adp().orbs.size(); ++i) {
                            Collections.swap(Wiz.adp().orbs, i, i - 1);
                        }

                        if (CustomOrbSlotManager.SlotFields.Crystal.get(o)) {
                            Wiz.applyToSelfTempstartTop(new FocusPower(AbstractDungeon.player, 1));
                            Wiz.att(new AbstractGameAction() {
                                    @Override
                                    public void update() {
                                        Wiz.adp().maxOrbs--;
                                        Wiz.adp().orbs.remove(o);
                                        isDone = true;
                                    }});
                            } else {
                                Wiz.adp().orbs.set(Wiz.adp().orbs.indexOf(o), new EmptyOrbSlot());
                            }

                            for (i = 0; i < Wiz.adp().orbs.size(); ++i) {
                                ((AbstractOrb) Wiz.adp().orbs.get(i)).setSlot(i, Wiz.adp().maxOrbs);
                            }
                            break;
                    }
                }
            }
            this.tickDuration();
    }
}