package dragonmod.patches;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.vfx.BobEffect;
import dragonmod.DragonMod;
import dragonmod.util.AbstractNotOrb;
import dragonmod.util.HymnManager;
import dragonmod.ui.TextureLoader;

@SpirePatch2(clz = AbstractPlayer.class, method = "render")
public class RenderActiveSealsPatch {
    public static float angle = MathUtils.random(360.0F);
    @SpirePrefixPatch
    public static void patch(AbstractPlayer __instance, SpriteBatch sb) {
        for (AbstractNotOrb seal : HymnManager.ActiveVerses){
            seal.update();
            seal.updateAnimation();
            seal.updateDescription();
            seal.renderText(sb);
            if (Loader.isModLoaded("LightsOut")){
                LightsOut.patches.CustomLightPatches.processCustomLights(seal);
            }
        }
        for (AbstractOrb orb : __instance.orbs){
            if (orb != null && !(orb instanceof EmptyOrbSlot)) {
                if (CustomOrbSlotManager.SlotFields.Crystal.get(orb)) {
                    Texture Crystal = TextureLoader.getTexture(DragonMod.orbPath("empty2.png"));
                    sb.setColor(Settings.CREAM_COLOR.cpy());
                    BobEffect bobEffect = new BobEffect(3.0F * Settings.scale, 3.0F);
                    angle += Gdx.graphics.getDeltaTime() * 10.0F;
                    sb.draw(Crystal, orb.cX - 48.0F + bobEffect.y / 8.0F, orb.cY - 48.0F - bobEffect.y / 8.0F, 48.0F, 48.0F, 96.0F, 96.0F, 1.0f, 1.0f, angle, 0, 0, 96, 96, false, false);
                }
            }
        }
    }
}
