package dragonmod.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

@SpirePatch2(clz = AbstractPlayer.class, method = "render")
public class RenderActiveSealsPatch {
    public static float angle = MathUtils.random(360.0F);
    @SpirePrefixPatch
    public static void patch(AbstractPlayer __instance, SpriteBatch sb) {
    }
}
