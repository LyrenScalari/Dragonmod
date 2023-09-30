package dragonmod.actions;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dragonmod.ui.VFXContainer;

public class ThrowIcicleAction extends AbstractGameAction {
    public boolean spawned = false;
    private final AbstractGameEffect e;

    public ThrowIcicleAction(AbstractOrb orb, Hitbox target, Color color) {
        this.e = VFXContainer.icicleThrowEffect(orb, target, color, false, true);
    }
    public ThrowIcicleAction(Texture tex, float scale, Hitbox target, Color color) {
        this.e = VFXContainer.throwEffectNoAngle(tex,scale, target, color, false, true);
    }
    @Override
    public void update() {
        if (!spawned) {
            spawned = true;
            AbstractDungeon.effectList.add(e);
        }
        this.isDone = e.isDone;
    }
}
