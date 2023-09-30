package dragonmod.cards.Rimedancer.Special;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.actions.FlourishAction;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.powers.Rimedancer.PrecisionPower;
import dragonmod.util.Wiz;

public class DazzlingShiv extends AbstractRimedancerCard {
    public static final String ID = DazzlingShiv.class.getSimpleName();
    public DazzlingShiv(){
        super(ID,0,CardType.ATTACK,CardRarity.SPECIAL,CardTarget.ENEMY,true);
        setDamage(4,2);
        setExhaust(true);
        tags.add(PrecisionPower.Shiv);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL));
        Wiz.atb(new FlourishAction());
    }
}
