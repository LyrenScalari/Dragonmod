package dragonmod.cards.Rimedancer.Common;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LockOnPower;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.powers.Rimedancer.BleedPower;
import dragonmod.powers.Rimedancer.PrecisionPower;
import dragonmod.util.Wiz;

public class RazorShiv extends AbstractRimedancerCard {
    public static final String ID = RazorShiv.class.getSimpleName();
    public RazorShiv(){
        super(ID,0,CardType.ATTACK,CardRarity.COMMON,CardTarget.ENEMY);
        setDamage(4,2);
        tags.add(PrecisionPower.Shiv);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL));
        if (m.hasPower(LockOnPower.POWER_ID)){
            Wiz.applyToEnemy(m,new BleedPower(m,m.getPower(LockOnPower.POWER_ID).amount));
        }
    }
}