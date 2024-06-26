package dragonmod.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class TargetedPetalEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private float vY;
    private float vX;
    private float scaleY;
    private int frame = 0;
    private float animTimer = 0.05F;
    private static final int W = 32;

    public TargetedPetalEffect(float posx,float posy) {
        this.x =posx;
        this.y = posy;
        this.frame = MathUtils.random(8);
        this.rotation = MathUtils.random(-10.0F, 10.0F);
        this.scale = MathUtils.random(1.0F, 2.5F);
        this.scaleY = MathUtils.random(1.0F, 1.2F);
        if (this.scale < 1.5F) {
            this.renderBehind = true;
        }

        this.vY = MathUtils.random(200.0F, 300.0F) * this.scale * Settings.scale;
        this.vX = MathUtils.random(-100.0F, 100.0F) * this.scale * Settings.scale;
        this.scale *= Settings.scale;
        if (MathUtils.randomBoolean()) {
            this.rotation += 180.0F;
        }

        this.color = new Color(1.0F, MathUtils.random(0.7F, 0.9F), MathUtils.random(0.7F, 0.9F), 1.0F);
        this.duration = 4.0F;// 36
    }// 37

    public void update() {
        this.y -= this.vY * Gdx.graphics.getDeltaTime();
        this.x += this.vX * Gdx.graphics.getDeltaTime();
        this.animTimer -= Gdx.graphics.getDeltaTime() / this.scale;
        if (this.animTimer < 0.0F) {
            this.animTimer += 0.05F;
            ++this.frame;
            if (this.frame > 11) {
                this.frame = 0;
            }
        }

        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        } else if (this.duration < 1.0F) {
            this.color.a = this.duration;
        }

    }// 57

    public void render(SpriteBatch sb) {
        sb.setColor(this.color);// 60
        switch (this.frame) {// 61
            case 0:
                this.renderImg(sb, ImageMaster.PETAL_VFX[0], false, false);// 63
                break;// 64
            case 1:
                this.renderImg(sb, ImageMaster.PETAL_VFX[1], false, false);// 66
                break;// 67
            case 2:
                this.renderImg(sb, ImageMaster.PETAL_VFX[2], false, false);// 69
                break;// 70
            case 3:
                this.renderImg(sb, ImageMaster.PETAL_VFX[3], false, false);// 72
                break;// 73
            case 4:
                this.renderImg(sb, ImageMaster.PETAL_VFX[2], true, true);// 75
                break;// 76
            case 5:
                this.renderImg(sb, ImageMaster.PETAL_VFX[1], true, true);// 78
                break;// 79
            case 6:
                this.renderImg(sb, ImageMaster.PETAL_VFX[0], true, true);// 81
                break;// 82
            case 7:
                this.renderImg(sb, ImageMaster.PETAL_VFX[1], true, true);// 84
                break;// 85
            case 8:
                this.renderImg(sb, ImageMaster.PETAL_VFX[2], true, true);// 87
                break;// 88
            case 9:
                this.renderImg(sb, ImageMaster.PETAL_VFX[3], true, true);// 90
                break;// 91
            case 10:
                this.renderImg(sb, ImageMaster.PETAL_VFX[2], false, false);// 93
                break;// 94
            case 11:
                this.renderImg(sb, ImageMaster.PETAL_VFX[1], false, false);// 96
        }

    }// 101

    public void dispose() {
    }// 105

    private void renderImg(SpriteBatch sb, Texture img, boolean flipH, boolean flipV) {
        sb.setBlendFunction(770, 1);// 108
        sb.draw(img, this.x, this.y, 16.0F, 16.0F, 32.0F, 32.0F, this.scale, this.scale * this.scaleY, this.rotation, 0, 0, 32, 32, flipH, flipV);// 109
        sb.setBlendFunction(770, 771);// 110
    }// 111
}
