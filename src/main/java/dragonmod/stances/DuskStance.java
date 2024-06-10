package dragonmod.stances;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.stance.DivinityParticleEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceChangeParticleGenerator;
import dragonmod.DragonMod;
import dragonmod.util.BlossomManager;
import dragonmod.util.Wiz;

public class DuskStance extends AbstractStance {
    public static final String STANCE_ID =  DragonMod.makeID("Dusk");
    public static int MaxPow = 2;
    private static final StanceStrings stanceString = CardCrawlGame.languagePack.getStanceString(STANCE_ID);
    public DuskStance() {
        this.ID = STANCE_ID;
        this.name = stanceString.NAME;// 23
        this.updateDescription();// 24
    }
    @Override
    public void updateDescription() {
        int totalpow = MaxPow + BlossomManager.getAmethystBlossom();

        StringBuilder builder = new StringBuilder();
        builder.append(stanceString.DESCRIPTION[0]);
        builder.append(totalpow);
        builder.append(stanceString.DESCRIPTION[1]);
        this.description = builder.toString();
    }
    public void updateAnimation() {
        if (!Settings.DISABLE_EFFECTS) {// 46
            this.particleTimer -= Gdx.graphics.getDeltaTime();// 48
            if (this.particleTimer < 0.0F) {// 49
                this.particleTimer = 0.05F;// 50
                AbstractDungeon.effectsQueue.add(new DivinityParticleEffect());// 51
            }
        }

        this.particleTimer2 -= Gdx.graphics.getDeltaTime();// 56
        if (this.particleTimer2 < 0.0F) {// 57
            this.particleTimer2 = MathUtils.random(0.3F, 0.4F);// 58
            AbstractDungeon.effectsQueue.add(new StanceAuraEffect("Calm"));// 59
        }

    }// 61
    public void onEnterStance() {
        AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.NAVY, true));
        AbstractDungeon.effectsQueue.add(new StanceChangeParticleGenerator(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, "Calm"));
    }
    public void onExitStance() {
        MaxPow = 2;
        BlossomManager.addAmberBlossom(1);
        this.stopIdleSfx();
    }
    public void atStartOfTurn() {
        int totalpow = MaxPow + BlossomManager.getAmethystBlossom();
        if (totalpow > 0){
            Wiz.atb(new DrawCardAction(totalpow));
            MaxPow -= 1;
        }
    }
}