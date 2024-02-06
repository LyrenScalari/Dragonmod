package dragonmod.stances;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.stance.DivinityParticleEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceChangeParticleGenerator;
import dragonmod.DragonMod;
import dragonmod.actions.ThrowShurikenAction;
import dragonmod.interfaces.onDrawCard;
import dragonmod.util.Wiz;

public class DuskStance extends AbstractStance implements onDrawCard {
    public static final String STANCE_ID =  DragonMod.makeID("Dusk");
    private static final OrbStrings stanceString = CardCrawlGame.languagePack.getOrbString(STANCE_ID);
    public DuskStance() {
        this.ID = STANCE_ID;
        this.name = stanceString.NAME;// 23
        this.updateDescription();// 24
    }
    @Override
    public void updateDescription() {
        this.description = stanceString.DESCRIPTION[0];
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
    @Override
    public void OnDrawCard() {
        if (!AbstractDungeon.actionManager.turnHasEnded){
            Wiz.att(new AbstractGameAction() {
                @Override
                public void update() {
                    AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
                    if (target != null){
                        Wiz.att(new DamageAction(target,new DamageInfo(AbstractDungeon.player,2, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                        Wiz.att(new ThrowShurikenAction("shuriken",1,target.hb, Color.GRAY.cpy(),false));
                    }
                    isDone = true;
                }
            });
        }
    }
}