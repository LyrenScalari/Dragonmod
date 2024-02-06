package dragonmod.actions;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dragonmod.DragonMod;
import dragonmod.ui.TextureLoader;
import dragonmod.ui.VFXContainer;



public class ThrowShurikenAction extends AbstractGameAction {
    //private static final float THROW_TIME = 0.25f;
    public boolean spawned = false;
    private final AbstractGameEffect e;

    public ThrowShurikenAction(String item, float scale, Hitbox target, Color color) {
        this(TextureLoader.getTexture(DragonMod.itemPath(item+".png")), scale, target, color, true);
    }

    public ThrowShurikenAction(String item, float scale, Hitbox target, Color color, boolean bounceOff) {
        this(TextureLoader.getTexture(DragonMod.itemPath(item+".png")), scale, target, color, bounceOff);
    }

    public ThrowShurikenAction(Texture tex, float scale, Hitbox target, Color color, boolean bounceOff) {
        this.e = VFXContainer.throwShurikenEffect(tex, scale, target, color, bounceOff, true);
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
