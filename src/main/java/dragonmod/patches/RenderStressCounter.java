package dragonmod.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static dragonmod.DragonMod.*;

public class RenderStressCounter {
    private static boolean Rendering = false;
    @SpirePatch(clz = EnergyPanel.class, method = "renderOrb", paramtypes = { "com.badlogic.gdx.graphics.g2d.SpriteBatch"})
    public static class RenderPatch{
        public static void Prefix(EnergyPanel __instance, SpriteBatch sb){
            if(getStressRender()){
                renderStressCounter(sb, __instance.current_x);
            }
        }
    }

    @SpirePatch(clz = EnergyPanel.class, method = "update")
    public static class UpdatePatch{
        public static void Prefix(EnergyPanel __instance){
            // Only update when rendering elements counter
            if (getStressRender()) {
                updateStressCounter();
            }
        }
    }
}