package dragonmod.cards.Rimedancer.Common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.orbs.Icicle;
import dragonmod.util.Wiz;

public class Misdirect extends AbstractRimedancerCard {

    public static final String ID = Misdirect.class.getSimpleName();
    public Misdirect(){
        super(ID,1,CardType.ATTACK,CardRarity.COMMON,CardTarget.ENEMY);
        setDamage(7,3);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        for (AbstractOrb o : p.orbs){
            if (o instanceof Icicle){
                Wiz.atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        isDone = true;
                        p.orbs.get(p.orbs.indexOf(o)).onStartOfTurn();
                        p.orbs.get(p.orbs.indexOf(o)).onEndOfTurn();
                    }
                });
                break;
            }
        }
    }
}
