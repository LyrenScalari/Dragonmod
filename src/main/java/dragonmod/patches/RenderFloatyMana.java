package dragonmod.patches;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import theDragonkin.util.TypeEnergyHelper;

import java.util.Map;

import static theDragonkin.DragonkinMod.TypeEnergyAtlas;

@SpirePatch2(clz = AbstractPlayer.class, method = "render")

public class RenderFloatyMana {
    public static float angleSpeed = 0.10f;
    private static float angle;
    private static  float dy = 220;
    public static  float dx = 0;
    private static  float dy2 = 210;
    public static  float dx2 = 0;
    private static final float renderScale = 2f;
    private static float particleTimer;
    private static final float INTERVAL = 0.1f;
    public static float amplitude;
    public static float bobTimer;
    public static float bobX;
    public static int manacount;
    @SpirePrefixPatch
    public static void patch(AbstractPlayer __instance, SpriteBatch sb) {
        particleTimer -= Gdx.graphics.getRawDeltaTime();
        if (angleSpeed > 1) {
            angleSpeed -= (Gdx.graphics.getDeltaTime());
        }
        angle -= 100*angleSpeed*Gdx.graphics.getDeltaTime();
        angle %= 360;
        float fScaleX = FontHelper.SCP_cardEnergyFont.getData().scaleX;
        FontHelper.SCP_cardEnergyFont.getData().setScale(0.50F);
        sb.setColor(Color.WHITE.cpy());
        for(Map.Entry<TypeEnergyHelper.Mana,Integer> e : TypeEnergyHelper.currentMana.entrySet()){
            if (e.getValue() != 0){
                manacount += 1;
            }
        }
        float da = 360f/manacount;
        float a = 0;
        int counter = 0;
        for(Map.Entry<TypeEnergyHelper.Mana,Integer> e : TypeEnergyHelper.currentMana.entrySet()){
            TextureAtlas.AtlasRegion img;
            float circleX = __instance.hb.cX + (float)(dy2*Math.cos((Math.toRadians(a+angle))));
            float circleY = (__instance.hb.cY+25) + (float)(dy*Math.sin(Math.toRadians(a+angle)));
            switch (e.getKey()) {
                case Red:
                    img = TypeEnergyAtlas.findRegion("[Red]");
                    break;
                case Black:
                    img = TypeEnergyAtlas.findRegion("[Black]");
                    break;
                case Blue:
                    img = TypeEnergyAtlas.findRegion("[Blue]");
                    break;
                case Rakados:
                    img = TypeEnergyAtlas.findRegion("[Rakados]");
                    break;
                case Dimir:
                    img = TypeEnergyAtlas.findRegion("[Dimir]");
                    break;
                case Izzet:
                    img = TypeEnergyAtlas.findRegion("[Izzet]");
                    break;
                case Colorless:
                    img = TypeEnergyAtlas.findRegion("[Grey]");
                    break;
                default:
                    throw new IllegalStateException("Unexpected mana type");
            }
            if (e.getValue() != 0) {
                sb.draw(img, circleX - img.packedWidth / 2f, circleY - img.packedHeight / 2f,
                        img.packedWidth / 2, img.packedHeight / 2,
                        img.packedWidth, img.packedHeight, renderScale * Settings.scale, renderScale * Settings.scale, 0);
                FontHelper.renderFont(sb, FontHelper.SCP_cardEnergyFont, e.getValue() + "", circleX + (img.packedWidth - 6), circleY - img.packedHeight / 2f
                        , Settings.CREAM_COLOR);
                a += da;
            }
        }
        if (particleTimer < 0.0F) {
            particleTimer = INTERVAL/angleSpeed;
        }
        manacount = 0;
        FontHelper.SCP_cardEnergyFont.getData().setScale(fScaleX);
    }
}
