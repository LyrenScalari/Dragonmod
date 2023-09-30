package dragonmod.cards.Justicar.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.powers.Dragonkin.Scorchpower;
import dragonmod.util.Wiz;

public class Chiwen extends AbstractJusticarCard {
    public static final String ID = Chiwen.class.getSimpleName();
    public Chiwen(){
        super(ID,1,CardType.ATTACK,CardRarity.COMMON,CardTarget.ALL_ENEMY);
        setDamage(7,3);
        setMagic(3,4);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DamageAllEnemiesAction(p,baseDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE));
        AbstractDungeon.effectsQueue.add(new InflameEffect(p));
        Wiz.applyToSelf(new Scorchpower(p,p,magicNumber));
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters){
            AbstractDungeon.effectsQueue.add(new InflameEffect(mo));
            Wiz.dmg(p,new DamageInfo(p,magicNumber, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE);
        }
    }
}