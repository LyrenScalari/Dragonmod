package dragonmod.actions;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dragonmod.ui.VFXContainer;

import java.util.ArrayList;

public class IcicleFanAction extends AbstractGameAction {
    public boolean spawned = false;
    private ArrayList<AbstractGameEffect> e = new ArrayList();
    public IcicleFanAction(AbstractOrb orb, ArrayList<Hitbox> targets, Color color) {
        for (Hitbox hb : targets){
            this.e.add( VFXContainer.icicleThrowEffect(orb, hb, color, false, true));
        }
    }
    public IcicleFanAction(ArrayList<AbstractOrb> orbs, Hitbox target, Color color) {
        for (AbstractOrb orb : orbs){
            this.e.add(VFXContainer.icicleThrowEffect(orb, target, color, false, true));
        }
    }
    public IcicleFanAction(Texture tex, float scale,  ArrayList<Hitbox> targets, Color color, boolean bounceOff) {
        for (Hitbox hb : targets) {
            this.e.add(VFXContainer.throwEffect(tex, scale, hb, color, bounceOff, true));
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
