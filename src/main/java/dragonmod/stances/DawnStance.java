package dragonmod.stances;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceChangeParticleGenerator;
import com.megacrit.cardcrawl.vfx.stance.WrathParticleEffect;
import dragonmod.DragonMod;
import dragonmod.util.Wiz;

public class DawnStance extends AbstractStance {
    public static final String STANCE_ID = DragonMod.makeID("Dawn");
    private static final OrbStrings stanceString = CardCrawlGame.languagePack.getOrbString(STANCE_ID);
    public DawnStance() {
        this.ID = STANCE_ID;
        this.name = stanceString.NAME;// 23
        this.updateDescription();// 24
    }
    public void updateAnimation() {
        if (!Settings.DISABLE_EFFECTS) {// 46
            this.particleTimer -= Gdx.graphics.getDeltaTime();// 48
            if (this.particleTimer < 0.0F) {// 49
                this.particleTimer = 0.05F;// 50
                AbstractDungeon.effectsQueue.add(new WrathParticleEffect());// 51
            }
        }

        this.particleTimer2 -= Gdx.graphics.getDeltaTime();// 56
        if (this.particleTimer2 < 0.0F) {// 57
            this.particleTimer2 = MathUtils.random(0.3F, 0.4F);// 58
            AbstractDungeon.effectsQueue.add(new StanceAuraEffect("Divinity"));// 59
        }

    }// 61
    public void onEnterStance() {
        AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.SCARLET, true));
        AbstractDungeon.effectsQueue.add(new StanceChangeParticleGenerator(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, "Divinity"));
    }
    public void onPlayCard(AbstractCard card) {
        if (card.type == AbstractCard.CardType.SKILL) {
            if (!DragonMod.CheckLastType(AbstractCard.CardType.SKILL)) {
                Wiz.block(AbstractDungeon.player, 4);
            }
        }
    }
    @Override
    public void updateDescription() {
        this.description = stanceString.DESCRIPTION[0];
    }
}
