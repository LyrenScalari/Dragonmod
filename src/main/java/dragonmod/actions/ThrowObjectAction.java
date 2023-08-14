package dragonmod.actions;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dragonmod.ui.VFXContainer;

public class ThrowObjectAction extends AbstractGameAction {
    public boolean spawned = false;
    private final AbstractGameEffect e;

    public ThrowObjectAction(Texture tex, float scale, Hitbox target, Color color, boolean bounceOff) {
        this.e = VFXContainer.throwEffect(tex, scale, target, color, bounceOff, true);
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