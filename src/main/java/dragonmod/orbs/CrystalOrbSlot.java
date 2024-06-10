package dragonmod.orbs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.powers.FocusPower;
import dragonmod.DragonMod;
import dragonmod.patches.CustomOrbSlotManager;
import dragonmod.ui.TextureLoader;
import dragonmod.util.Wiz;

public class CrystalOrbSlot extends SpecialOrbSlot {
    public static final String ORB_ID = DragonMod.makeID("CrystalSlot");
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    public CrystalOrbSlot(float x, float y) {
        this.angle = MathUtils.random(360.0F);
        img = TextureLoader.getTexture(DragonMod.orbPath("empty1.png"));
        this.ID = ORB_ID;
        this.name = orbString.NAME;
        this.evokeAmount = 0;
        this.cX = x;
        this.cY = y;
        this.updateDescription();
        this.channelAnimTimer = 0.5F;
    }

    public CrystalOrbSlot() {
        this.angle = MathUtils.random(360.0F);
        img = TextureLoader.getTexture(DragonMod.orbPath("empty1.png"));
        this.name = orbString.NAME;
        this.evokeAmount = 0;
        this.cX = AbstractDungeon.player.drawX + AbstractDungeon.player.hb_x;
        this.cY = AbstractDungeon.player.drawY + AbstractDungeon.player.hb_y + AbstractDungeon.player.hb_h / 2.0F;
        this.updateDescription();
        CustomOrbSlotManager.SlotFields.SlotType.set(this, CustomOrbSlotManager.SlotFields.SlotTypes.Crystal);
    }
    public SpireReturn<Void> ContainedOrbRemoved(AbstractOrb Source, boolean removal){
        if (!removal){
            Source.onEvoke();
        }
        Wiz.applyToSelfTempstartTop(new FocusPower(AbstractDungeon.player,1));
        Wiz.Player().maxOrbs--;
        Wiz.Player().orbs.remove(Source);
        int i;
        for(i = 0; i < Wiz.Player().orbs.size(); ++i) {
            ((AbstractOrb)Wiz.Player().orbs.get(i)).setSlot(i, Wiz.Player().maxOrbs);
        }
        return SpireReturn.Return();
    }

    public void updateDescription() {
        this.description = orbString.DESCRIPTION[0] + orbString.DESCRIPTION[1];
    }

    public void onEvoke() {
    }
    public void SlotTip(AbstractOrb Source){
        TipHelper.renderGenericTip(Source.tX + 48.0F * Settings.scale, Source.tY + 64.0F * Settings.scale,orbString.NAME, orbString.DESCRIPTION[2]);
    }
    public void updateAnimation() {
        super.updateAnimation();
        this.angle += Gdx.graphics.getDeltaTime() * 10.0F;
    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.c);
        sb.draw(img, this.cX - 48.0F + this.bobEffect.y / 8.0F, this.cY - 48.0F - this.bobEffect.y / 8.0F, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, this.angle, 0, 0, 96, 96, false, false);
        this.renderText(sb);
        this.hb.render(sb);
    }

    public AbstractOrb makeCopy() {
        return new EmptyOrbSlot();
    }

    public void playChannelSFX() {
    }
}
