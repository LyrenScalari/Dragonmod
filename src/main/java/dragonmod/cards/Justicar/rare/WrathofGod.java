package dragonmod.cards.Justicar.rare;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.ui.DivineEyeParticle;
import dragonmod.ui.HaloEffect;
import dragonmod.util.Wiz;

public class WrathofGod extends AbstractJusticarCard {

    public static final String ID = WrathofGod.class.getSimpleName();
    public WrathofGod(){
        super(ID,1,CardType.ATTACK,CardRarity.RARE,CardTarget.ALL_ENEMY);
        setCustomVar("WOG",8,2);
        setVarCalculation("WOG", (m, base) -> {
            if (AbstractDungeon.player != null){
                int tmp = this.baseDamage;
                this.baseDamage = base;
                if (m != null)
                    super.calculateCardDamage(m);
                else
                    super.applyPowers();
                this.baseDamage = tmp;
                return damage + AbstractDungeon.player.currentBlock;
            } else {
                return base;
            }
        });
        setExhaust(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardCrawlGame.sound.play("POWER_MANTRA", 0.05F);
        Wiz.att(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                for (int i = 0; i < AbstractDungeon.miscRng.random(20, 30); ++i) {
                    AbstractDungeon.effectsQueue.add(new DivineEyeParticle());
                }
            }
        });
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters){
            if (!mo.isDeadOrEscaped()){
                Wiz.vfx(new HaloEffect(mo, Color.GOLD,Color.GOLDENROD,""));
                Wiz.vfx(new LightningEffect(mo.hb.cX,mo.hb.cY));
                Wiz.dmg(mo,new DamageInfo(p,customVar("WOG"), DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE);
            }
        }
    }
}
