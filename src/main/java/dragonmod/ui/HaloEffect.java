package dragonmod.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class HaloEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private TextureAtlas.AtlasRegion img;
    private Color altColor;
    private String sfxUrl = "HEAL_3";

    public HaloEffect(AbstractCreature target, Color setColor, Color altColor, String setSfx) {
        if (this.img == null) {// 22
            this.img = ImageMaster.CRYSTAL_IMPACT;// 23
        }

        this.x = target.hb.cX - (float)this.img.packedWidth / 2.0F;// 26
        this.y = target.hb.cY - (float)this.img.packedHeight / 2.0F;// 27
        this.startingDuration = 0.7F;// 29
        this.duration = this.startingDuration;// 30
        this.scale = Settings.scale;// 31
        this.altColor = altColor;// 32
        this.color = setColor.cpy();// 33
        this.color.a = 0.0F;// 34
        this.renderBehind = false;// 35
        this.sfxUrl = setSfx;// 36
    }// 37

    public void update() {
        if (this.duration == this.startingDuration && !sfxUrl.equals("")) {// 57
            CardCrawlGame.sound.playA(this.sfxUrl, 0.5F);// 58
        }

        this.duration -= Gdx.graphics.getDeltaTime();// 61
        if (this.duration > this.startingDuration / 2.0F) {// 63
            this.color.a = Interpolation.fade.apply(1.0F, 0.01F, this.duration - this.startingDuration / 2.0F) * Settings.scale;// 64
        } else {
            this.color.a = Interpolation.fade.apply(0.01F, 1.0F, this.duration / (this.startingDuration / 2.0F)) * Settings.scale;// 66
        }

        this.scale = Interpolation.pow5In.apply(2.4F, 0.3F, this.duration / this.startingDuration) * Settings.scale;// 69
        if (this.duration < 0.0F) {// 71
            this.isDone = true;// 72
        }

    }// 74

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);// 78
        this.altColor.a = this.color.a;// 79
        sb.setColor(this.altColor);// 80
        sb.draw(this.img, this.x, this.y, (float)this.img.packedWidth / 2.0F, (float)this.img.packedHeight / 2.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, this.scale * 1.1F, this.scale * 1.1F, 0.0F);// 81
        sb.setColor(this.color);// 93
        sb.draw(this.img, this.x, this.y, (float)this.img.packedWidth / 2.0F, (float)this.img.packedHeight / 2.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, this.scale * 0.9F, this.scale * 0.9F, 0.0F);// 94
        sb.setBlendFunction(770, 771);// 105
    }// 106

    public void dispose() {
    }// 110
}
