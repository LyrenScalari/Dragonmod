package dragonmod.cards.Justicar;


import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import com.megacrit.cardcrawl.vfx.combat.SearingBlowEffect;
import dragonmod.powers.Dragonkin.Scorchpower;
import dragonmod.util.TriggerOnCycleEffect;


public class Flamestrike extends AbstractPrimalCard implements TriggerOnCycleEffect {
    public static final String ID = Flamestrike.class.getSimpleName();
    public int Intensity = 0;

    public Flamestrike() {
        super(ID,1,CardType.ATTACK,CardRarity.RARE,CardTarget.ALL_ENEMY );
        setDamage(12,4);
        setMagic(2,2);
        setMagic2(4,2);
        this.tags.add(AbstractCard.CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!mo.isDeadOrEscaped()) {
            addToBot(new VFXAction(new InflameEffect(mo)));
            addToBot(new VFXAction(new SearingBlowEffect(mo.hb.cX, mo.hb.cY, Intensity)));
            addToBot(new DamageAction(mo, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
            addToBot(new ApplyPowerAction(mo,p,new Scorchpower(mo,p,SecondMagicNumber)));
            }
        }
        super.use(p,m);
    }

    @Override
    public void TriggerOnCycle(AbstractCard ca) {
        Intensity += 1;
        baseDamage += magicNumber;
        BaseSecondMagicNumber += magicNumber;
    }
}