package dragonmod.cards.Rimedancer.Uncommon;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.powers.Rimedancer.PrecisionPower;
import dragonmod.powers.Rimedancer.Subzero;
import dragonmod.util.Wiz;

public class SparklingShiv extends AbstractRimedancerCard {
    public static final String ID = SparklingShiv.class.getSimpleName();

    public SparklingShiv() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        setDamage(4, 2);
        tags.add(PrecisionPower.Shiv);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL));
        if (m.hasPower(VulnerablePower.POWER_ID)){
            Wiz.applyToSelfNextTurn(new Subzero(p,p,m.getPower(VulnerablePower.POWER_ID).amount));
        }
    }
}