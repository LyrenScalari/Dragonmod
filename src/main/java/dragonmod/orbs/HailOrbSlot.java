package dragonmod.orbs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import dragonmod.DragonMod;
import dragonmod.actions.ThrowIcicleAction;
import dragonmod.ui.TextureLoader;
import dragonmod.util.Wiz;

import java.util.Collections;

public class HailOrbSlot extends SpecialOrbSlot {
    public static final String ORB_ID = DragonMod.makeID("HailSlot");
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    public HailOrbSlot(float x, float y) {
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

    public HailOrbSlot() {
        this.angle = MathUtils.random(360.0F);
        img = TextureLoader.getTexture(DragonMod.orbPath("empty1.png"));
        this.name = orbString.NAME;
        this.evokeAmount = 0;
        this.cX = AbstractDungeon.player.drawX + AbstractDungeon.player.hb_x;
        this.cY = AbstractDungeon.player.drawY + AbstractDungeon.player.hb_y + AbstractDungeon.player.hb_h / 2.0F;
        this.updateDescription();
    }
    public SpireReturn<Void> ContainedOrbRemoved(AbstractOrb Source, boolean removal){
        if (!removal){
            Source.onEvoke();
        }
        Wiz.att(new AbstractGameAction() {
            @Override
            public void update() {
                Wiz.Player().maxOrbs--;
                Wiz.Player().orbs.remove(Source);
                isDone = true;
            }
        });
        int i;
        for(i = 1; i < Wiz.Player().orbs.size(); ++i) {
            Collections.swap(Wiz.Player().orbs, i, i - 1);
        }
        for(i = 0; i < Wiz.Player().orbs.size(); ++i) {
            ((AbstractOrb)Wiz.Player().orbs.get(i)).setSlot(i, Wiz.Player().maxOrbs);
        }
        return SpireReturn.Return();
    }
    public  void ContainedOrbTurnStart(AbstractOrb Source){
        for (int i = 0; i < 2; i++) {
            AbstractCreature m = AbstractDungeon.getRandomMonster();
            if (m != null) {
                DamageInfo info = new DamageInfo(AbstractDungeon.player, Source.passiveAmount, DamageInfo.DamageType.THORNS);
                info.output = AbstractOrb.applyLockOn(m, info.base);
                Wiz.atb(new ThrowIcicleAction(Source, m.hb, Color.CYAN));
                Wiz.dmg(m, info, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
            }
        }
    }
    public void SlotTip(AbstractOrb Source){
        TipHelper.renderGenericTip(this.tX + 96.0F * Settings.scale, this.tY + 128.0F * Settings.scale,orbString.NAME, orbString.DESCRIPTION[1]+Source.passiveAmount+DESCRIPTIONS[2]);
    }
    public void updateDescription() {
        this.description = orbString.DESCRIPTION[0];
    }

    public void updateAnimation() {
        super.updateAnimation();
        this.angle += Gdx.graphics.getDeltaTime() * 5.0F;
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