package dragonmod.cards.Rimedancer.Uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.powers.Rimedancer.SilverShadowPower;
import dragonmod.util.Wiz;

public class SilverShadow extends AbstractRimedancerCard {
    public static final String ID = SilverShadow.class.getSimpleName();
    public SilverShadow(){
        super(ID,1,CardType.ATTACK,CardRarity.UNCOMMON,CardTarget.ENEMY);
        setDamage(7,3);
        setMagic(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY);
        Wiz.applyToSelf(new SilverShadowPower(p,magicNumber));
    }
}