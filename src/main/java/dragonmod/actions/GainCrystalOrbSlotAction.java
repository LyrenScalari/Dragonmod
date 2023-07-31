package dragonmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import dragonmod.util.Wiz;

public class GainCrystalOrbSlotAction extends AbstractGameAction {
    public GainCrystalOrbSlotAction(int slotIncrease) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = slotIncrease;
        this.actionType = ActionType.BLOCK;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            for(int i = 0; i < this.amount; ++i) {
                if (AbstractDungeon.player.maxOrbs == 10) {
                    AbstractDungeon.effectList.add(new ThoughtBubble(Wiz.adp().dialogX, Wiz.adp().dialogY, 3.0F, AbstractPlayer.MSG[3], true));
                } else {
                    CardCrawlGame.sound.play("ORB_SLOT_GAIN", 0.1F);
                    AbstractDungeon.player.maxOrbs += amount;

                    for(int ii = 0; ii < amount; ++ii) {
                        Wiz.adp().orbs.add(new CrystalOrbSlot());
                    }

                    for(int iii = 0; iii < Wiz.adp().orbs.size(); ++iii) {
                        ((AbstractOrb)Wiz.adp().orbs.get(iii)).setSlot(iii, Wiz.adp().maxOrbs);
                    }

                }
            }
        }

        this.tickDuration();
    }
}