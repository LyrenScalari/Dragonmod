package dragonmod.orbs;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;

public class SpecialOrbSlot extends EmptyOrbSlot {
    public Texture overlayimg;
    public SpecialOrbSlot(float x, float y) {
    }
    public SpecialOrbSlot() {
    }
    public  void ContainedOrbRemoved(AbstractOrb Source){}
    public  void ContainedOrbTurnStart(AbstractOrb Source){}
    public  void ContainedOrbTurnEnd(AbstractOrb Source){}
    public  void ContainedOrbApplyFocus(AbstractOrb Source){}
}
