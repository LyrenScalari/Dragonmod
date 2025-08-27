package dragonmod.ui;

import basemod.ReflectionHacks;
import basemod.helpers.VfxBuilder;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dragonmod.orbs.Icicle;
import dragonmod.util.Wiz;

public class VFXContainer {
    public static AbstractGameEffect hitBounce(Texture tex, float scale, Hitbox target) {
        return new VfxBuilder(tex, target.cX, target.cY,1.5f)
                .setScale(scale)
                .gravity(50f)
                .velocity(MathUtils.random(45f, 135f), MathUtils.random(600f, 800f))
                .rotate(MathUtils.random(50f, 200f) * (MathUtils.randomBoolean() ? -1 : 1))
                .build();
    }

    public static AbstractGameEffect throwEffect(Texture tex, float scale, Hitbox target, Color color, boolean bounceOff, boolean sfx) {
        Vector2 vector = new Vector2(target.cX-Wiz.Player().hb.cX,target.cY-Wiz.Player().hb.cY);
        float angle =0;
        float targetAngle = vector.angle();
        VfxBuilder builder = new VfxBuilder(tex, Wiz.Player().hb.cX, Wiz.Player().hb.cY, 0.5f)
                .moveX(Wiz.Player().hb.cX, target.cX, VfxBuilder.Interpolations.POW2OUT)
                .moveY(Wiz.Player().hb.cY, target.cY, VfxBuilder.Interpolations.POW2OUT)
                .setAngle(targetAngle)
                .setScale(scale)
                .emitEvery((x,y) -> new ThrowSparkleEffect(color.cpy(), x, y), 0.01f);
        if (sfx) {
            builder = builder.playSoundAt(0.0f, "ATTACK_WHIFF_2");
        }
        if (bounceOff) {
            builder = builder.triggerVfxAt(0.25f, 1, (x,y) -> hitBounce(tex, scale, target));
        }
        return builder.build();
    }
    public static AbstractGameEffect throwShurikenEffect(Texture tex, float scale, Hitbox target, Color color, boolean bounceOff, boolean sfx) {
        VfxBuilder builder = new VfxBuilder(tex, Wiz.Player().hb.cX, Wiz.Player().hb.cY, 0.5f)
                .moveX(Wiz.Player().hb.cX, target.cX, VfxBuilder.Interpolations.POW2OUT)
                .moveY(Wiz.Player().hb.cY, target.cY, VfxBuilder.Interpolations.POW2OUT)
                .rotate(250f)
                .setScale(scale)
                .emitEvery((x,y) -> new ThrowSparkleEffect(color.cpy(), x, y), 0.01f);
        if (sfx) {
            builder = builder.playSoundAt(0.0f, "ATTACK_WHIFF_2");
        }
        if (bounceOff) {
            builder = builder.triggerVfxAt(0.25f, 1, (x,y) -> hitBounce(tex, scale, target));
        }
        return builder.build();
    }
    public static AbstractGameEffect VolleyShurikenEffect(Texture tex, float scale, Hitbox target, Color color, boolean bounceOff, boolean sfx) {
        VfxBuilder builder = new VfxBuilder(tex, Wiz.Player().hb.cX, Wiz.Player().hb.cY, 0.25f)
                .arc(Wiz.Player().hb.cX, Wiz.Player().hb.cY,  target.cX, target.cY - (target.height / 2), Wiz.Player().hb.cY*2)
                .rotate(MathUtils.random(100f, 300f) * (MathUtils.randomBoolean() ? -1 : 1))
                .setScale(scale)
                .emitEvery((x,y) -> new ThrowSparkleEffect(color.cpy(), x, y), 0.01f);
        if (sfx) {
            builder = builder.playSoundAt(0.0f, "ATTACK_WHIFF_2");
        }
        if (bounceOff) {
            builder = builder.triggerVfxAt(0.25f, 1, (x,y) -> hitBounce(tex, scale, target));
        }
        return builder.build();
    }
    public static AbstractGameEffect throwEffectNoAngle(Texture tex, float scale, Hitbox target, Color color, boolean bounceOff, boolean sfx) {
        VfxBuilder builder = new VfxBuilder(tex, Wiz.Player().hb.cX, Wiz.Player().hb.cY, 0.5f)
                .moveX(Wiz.Player().hb.cX, target.cX, VfxBuilder.Interpolations.POW2OUT)
                .moveY(Wiz.Player().hb.cY, target.cY, VfxBuilder.Interpolations.POW2OUT)
                .rotate(0)
                .setScale(scale)
                .emitEvery((x,y) -> new ThrowSparkleEffect(color.cpy(), x, y), 0.01f);
        if (sfx) {
            builder = builder.playSoundAt(0.0f, "ATTACK_WHIFF_2");
        }
        if (bounceOff) {
            builder = builder.triggerVfxAt(0.25f, 1, (x,y) -> hitBounce(tex, scale, target));
        }
        return builder.build();
    }
    public static AbstractGameEffect icicleThrowEffect(AbstractOrb orb, Hitbox target, Color color, boolean bounceOff, boolean sfx) {
        Texture tex = ReflectionHacks.getPrivate(orb,AbstractOrb.class,"img");
        float scale = ReflectionHacks.getPrivate(orb,AbstractOrb.class,"scale");
        VfxBuilder builder;
        Vector2 vector = new Vector2(target.cX-Wiz.Player().hb.cX,target.cY-Wiz.Player().hb.cY);
        float targetAngle = vector.angle();
        if (orb instanceof Icicle){
            builder = new VfxBuilder(tex, orb.hb.cX, orb.hb.cY, 0.25f)
                    .moveX(orb.hb.cX, target.cX, VfxBuilder.Interpolations.POW2OUT)
                    .moveY(orb.hb.cY, target.cY, VfxBuilder.Interpolations.POW2OUT)
                    .setAngle(targetAngle)
                    .setScale(scale)
                    .emitEvery((x,y) -> new ThrowSparkleEffect(color.cpy(), x, y), 0.01f);
        } else {
            builder = new VfxBuilder(tex, orb.hb.cX, orb.hb.cY, 0.25f)
                    .moveX(orb.hb.cX, target.cX, VfxBuilder.Interpolations.POW2OUT)
                    .moveY(orb.hb.cY, target.cY, VfxBuilder.Interpolations.POW2OUT)
                    .rotate(0)
                    .setScale(scale)
                    .emitEvery((x, y) -> new ThrowSparkleEffect(color.cpy(), x, y), 0.01f);
        }
        if (sfx) {
            builder = builder.playSoundAt(0.0f, "ATTACK_WHIFF_2");
        }
        if (bounceOff) {
            builder = builder.triggerVfxAt(0.25f, 1, (x,y) -> hitBounce(tex, scale, target));
        }
        builder.whenStarted(vfxBuilder -> {
            if (orb instanceof Icicle){
                ((Icicle) orb).thrown = true;
            }
        });
        builder.whenComplete(vfxBuilder -> {if (orb instanceof Icicle){
            ((Icicle) orb).thrown = false;
        }
        });

        return builder.build();
    }
}
