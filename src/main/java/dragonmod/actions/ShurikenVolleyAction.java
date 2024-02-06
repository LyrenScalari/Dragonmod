package dragonmod.actions;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dragonmod.ui.VFXContainer;

import java.util.ArrayList;

public class ShurikenVolleyAction extends AbstractGameAction {
    public boolean spawned = false;
    private ArrayList<AbstractGameEffect> e = new ArrayList();
    public ShurikenVolleyAction(Texture tex, float scale,  ArrayList<Hitbox> targets, Color color, boolean bounceOff) {
        for (Hitbox hb : targets) {
            this.e.add(VFXContainer.throwShurikenEffect(tex, scale, hb, color, bounceOff, true));
        }
    }
    @Override
    public void update() {
        if (!spawned) {
            spawned = true;
            AbstractDungeon.effectList.addAll(e);
        }
        for (AbstractGameEffect effect : e){
            this.isDone = effect.isDone;
        }
    }
}
