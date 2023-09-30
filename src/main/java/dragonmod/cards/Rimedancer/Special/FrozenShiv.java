package dragonmod.cards.Rimedancer.Special;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.actions.FireAction;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.powers.Rimedancer.PrecisionPower;
import dragonmod.util.Wiz;

public class FrozenShiv extends AbstractRimedancerCard {
    public static final String ID = FrozenShiv.class.getSimpleName();
    public FrozenShiv(){
        super(ID,0,CardType.ATTACK,CardRarity.SPECIAL,CardTarget.ENEMY,true);
        setDamage(2,2);
        setExhaust(true);
        tags.add(PrecisionPower.Shiv);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL));
        Wiz.atb(new FireAction());
    }
}
