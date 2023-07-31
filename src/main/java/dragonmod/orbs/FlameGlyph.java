package dragonmod.orbs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import dragonmod.DragonMod;
import dragonmod.util.AbstractRune;

public class FlameGlyph extends AbstractRune {
    public static final String ORB_ID = DragonMod.makeID("Flame");
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    public FlameGlyph(){
        super();
        Sealstrings = orbString;
        PainAmount = 2;
        name = orbString.NAME;
        RuneText = DESCRIPTIONS[4];
        updateAnimation();
    }
    public void renderText(SpriteBatch sb) {
        FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.PainAmount), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET + 20.0F * Settings.scale,new Color(0.5F, 0.0F, 3.0F, this.c.a), this.fontScale);
    }
    public void updateDescription() {
        description = DESCRIPTIONS[0] + DESCRIPTIONS[1] + DESCRIPTIONS[2] + PainAmount + DESCRIPTIONS[3];
    }
}
