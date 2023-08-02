package dragonmod.cards.Justicar;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import com.megacrit.cardcrawl.vfx.stance.DivinityParticleEffect;
import dragonmod.powers.Dragonkin.DivineRetributionPower;

public class DivineRetribution extends AbstractHolyCard {
    public static final String ID = DivineRetribution.class.getSimpleName();

    public DivineRetribution() {
        super(ID, 2,CardType.POWER,CardRarity.RARE,CardTarget.SELF);
        setCostUpgrade(1);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardCrawlGame.sound.play("ORB_LIGHTNING_EVOKE");
        addToBot(new VFXAction(new LightningEffect(p.drawX,p.drawY)));
        CardCrawlGame.sound.play("POWER_MANTRA", 0.05F);
        for (int i = 0; i < AbstractDungeon.miscRng.random(15, 30); ++i) {
            AbstractDungeon.effectsQueue.add(new DivinityParticleEffect());
        }
        addToBot(new ApplyPowerAction(p,p,new DivineRetributionPower(p,p,0)));
        super.use(p,m);
    }
}