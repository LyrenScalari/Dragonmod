package dragonmod.cards.Rimedancer.Rare;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import dragonmod.actions.ExploitAction;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.powers.Rimedancer.SlowPower;
import dragonmod.util.Wiz;

public class TempoTheft extends AbstractRimedancerCard {
    public static final String ID = TempoTheft.class.getSimpleName();
    public TempoTheft(){
        super(ID,1,CardType.ATTACK,CardRarity.RARE,CardTarget.ENEMY);
        setDamage(8,4);
        setMagic(2,1);
        setMagic2(1);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL));
        Wiz.applyToEnemy(m,new SlowPower(m,SecondMagicNumber));
        Wiz.atb(new ExploitAction(()->new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                Wiz.applyToSelf(new DexterityPower(p,SecondMagicNumber));
                Wiz.applyToSelf(new DrawCardNextTurnPower(p,magicNumber));
            }
        },3,m));
    }
}
