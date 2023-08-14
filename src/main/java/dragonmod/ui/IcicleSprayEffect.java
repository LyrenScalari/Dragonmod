package dragonmod.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class IcicleSprayEffect extends AbstractGameEffect {
    private boolean flipX;

    public IcicleSprayEffect(boolean shouldFlip) {
        this.flipX = shouldFlip;
    }

    public void update() {
        this.isDone = true;
        int i;
        float x;
        if (this.flipX) {
            for (i = 12; i > 0; --i) {
                x = AbstractDungeon.player.hb.cX - MathUtils.random(0.0F, 450.0F) * Settings.scale;
                AbstractDungeon.effectsQueue.add(new FlyingIcicleEffect(x, AbstractDungeon.player.hb.cY + 120.0F * Settings.scale + (float) i * -18.0F * Settings.scale, (float) (i * 4) - 30.0F, true));
            }
        } else {
            for (i = 0; i < 12; ++i) {
                x = AbstractDungeon.player.hb.cX + MathUtils.random(0.0F, 450.0F) * Settings.scale;
                AbstractDungeon.effectsQueue.add(new FlyingIcicleEffect(x, AbstractDungeon.player.hb.cY - 100.0F * Settings.scale + (float) i * 18.0F * Settings.scale, (float) (i * 4) - 20.0F, false));
            }
        }

    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}