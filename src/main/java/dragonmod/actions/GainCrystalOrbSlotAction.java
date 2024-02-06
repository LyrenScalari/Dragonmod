package dragonmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import dragonmod.orbs.CrystalOrbSlot;
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
                    AbstractDungeon.effectList.add(new ThoughtBubble(Wiz.Player().dialogX, Wiz.Player().dialogY, 3.0F, AbstractPlayer.MSG[3], true));
                } else {
                    CardCrawlGame.sound.play("ORB_SLOT_GAIN", 0.1F);
                    AbstractDungeon.player.maxOrbs += 1;
                    Wiz.Player().orbs.add(new CrystalOrbSlot());

                    for(int iii = 0; iii < Wiz.Player().orbs.size(); ++iii) {
                        ((AbstractOrb)Wiz.Player().orbs.get(iii)).setSlot(iii, Wiz.Player().maxOrbs);
                    }

                }
            }
        }

        this.tickDuration();
    }
}