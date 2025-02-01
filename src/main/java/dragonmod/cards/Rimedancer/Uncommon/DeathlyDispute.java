package dragonmod.cards.Rimedancer.Uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LockOnPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import dragonmod.actions.ExploitAction;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.util.Wiz;

public class DeathlyDispute extends AbstractRimedancerCard {
    public static final String ID = DeathlyDispute.class.getSimpleName();
    public DeathlyDispute(){
        super(ID,1,CardType.ATTACK,CardRarity.UNCOMMON,CardTarget.ENEMY);
        setDamage(8,4);
        setMagic(2,1);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL));
        Wiz.applyToEnemy(m,new LockOnPower(m,magicNumber));
        Wiz.att(new ExploitAction(()->new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                Wiz.applyToSelf(new StrengthPower(p,magicNumber));
                Wiz.atb(new ExhaustSpecificCardAction(DeathlyDispute.this, Wiz.Player().discardPile));
            }
        },3,m));
    }
}
