package dragonmod.cards.Justicar.common;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.combat.FireballEffect;
import com.megacrit.cardcrawl.vfx.combat.FlameBarrierEffect;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.powers.Dragonkin.Scorchpower;
import dragonmod.util.Wiz;

public class Charr extends AbstractJusticarCard {

    public static final String ID = Charr.class.getSimpleName();

    public Charr() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        setMagic(2, 1);
        setMagic2(3,1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.vfx(new FlameBarrierEffect(p.drawX,p.drawY));
        Wiz.atb(new ApplyPowerAction(m,p,new VulnerablePower(m,magicNumber,false),magicNumber, AbstractGameAction.AttackEffect.FIRE));
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters){
            Wiz.vfx(new FireballEffect(p.drawX,p.drawY,mo.drawX,mo.drawY));
            Wiz.applyToEnemy(mo,new Scorchpower(mo,p,SecondMagicNumber));
        }
    }
}
