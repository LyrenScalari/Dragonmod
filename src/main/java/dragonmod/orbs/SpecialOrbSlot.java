package dragonmod.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;

public class SpecialOrbSlot extends EmptyOrbSlot {
    public Texture overlayimg;
    public boolean focused = true;
    public SpecialOrbSlot(float x, float y) {
    }
    public SpecialOrbSlot() {
    }
    public SpireReturn<Void> ContainedOrbRemoved(AbstractOrb Source, boolean removal){
        return SpireReturn.Continue();
    }
    public void ContainedOrbTurnStart(AbstractOrb Source){}
    public void ContainedOrbTurnEnd(AbstractOrb Source){}
    public void ContainedOrbApplyFocus(AbstractOrb Source){}
    public void SlotTip(AbstractOrb Source){
    }
}
