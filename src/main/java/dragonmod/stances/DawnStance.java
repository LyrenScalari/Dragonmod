package dragonmod.stances;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.PetalEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceChangeParticleGenerator;
import com.megacrit.cardcrawl.vfx.stance.WrathParticleEffect;
import dragonmod.DragonMod;
import dragonmod.actions.ThrowShurikenAction;
import dragonmod.util.BlossomManager;
import dragonmod.util.Wiz;

public class DawnStance extends AbstractStance {
    public static final String STANCE_ID = DragonMod.makeID("Dawn");
    public static int MaxPow = 4;
    private static final StanceStrings stanceString = CardCrawlGame.languagePack.getStanceString(STANCE_ID);
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
                AbstractDungeon.effectsQueue.add(new WrathParticleEffect());
            }
        }

        this.particleTimer2 -= Gdx.graphics.getDeltaTime();
        if (this.particleTimer2 < 0.0F) {
            this.particleTimer2 = MathUtils.random(0.3F, 0.4F);
            AbstractDungeon.effectsQueue.add(new StanceAuraEffect("Divinity"));
        }
        this.updateDescription();
    }
    public void onEnterStance() {
        MaxPow = 4;
        AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.SCARLET, true));
        AbstractDungeon.effectsQueue.add(new StanceChangeParticleGenerator(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, "Divinity"));
    }
    public void onExitStance() {
        MaxPow = 4;
        BlossomManager.addAmethystBlossom(1);
        this.stopIdleSfx();
    }
    public void onPlayCard(AbstractCard card) {
        int totalpow = MaxPow+ BlossomManager.getAmberBlossom();
        if (card.type == AbstractCard.CardType.ATTACK){
            if (totalpow > 0 && !(Wiz.Player().hand.isEmpty())) {
                Wiz.atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
                        for (int i = 0; i < 12; i++){
                            AbstractDungeon.effectsQueue.add(new PetalEffect());
                        }
                        if (target != null) {
                            if (Wiz.Player().hand.size() >= totalpow) {
                                Wiz.att(new DamageAction(target, new DamageInfo(AbstractDungeon.player, totalpow, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                            } else {
                                Wiz.att(new DamageAction(target, new DamageInfo(AbstractDungeon.player, Wiz.Player().hand.size()+1, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                            }
                            Wiz.att(new ThrowShurikenAction("shuriken", 1, target.hb, Color.GRAY.cpy(), false));
                        }
                        isDone = true;
                    }
                });
                MaxPow -= 1;
            }
        }
    }

    @Override
    public void updateDescription() {
        int totalpow = MaxPow + BlossomManager.getAmberBlossom();
        StringBuilder builder = new StringBuilder();
        builder.append(stanceString.DESCRIPTION[0]);

        if (Wiz.Player().hand.size() >= totalpow) {
            builder.append(totalpow);
        } else {
            builder.append(Wiz.Player().hand.size());
        }

        builder.append(stanceString.DESCRIPTION[1]);
        builder.append(totalpow);
        builder.append(stanceString.DESCRIPTION[2]);
        this.description = builder.toString();
    }
}
