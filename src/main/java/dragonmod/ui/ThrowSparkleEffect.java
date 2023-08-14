package dragonmod.ui;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.vfx.UncommonPotionParticleEffect;


public class ThrowSparkleEffect extends UncommonPotionParticleEffect {
    public ThrowSparkleEffect(Color color, float x, float y) {
        super(x, y);
        this.color = color;
    }
}
