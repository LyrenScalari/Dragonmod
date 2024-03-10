package dragonmod.cards.Rimedancer.Special;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.Wiz;

public class Ambush extends AbstractRimedancerCard {
    public static final String ID = Ambush.class.getSimpleName();
    public Ambush(){
        super(ID,1,CardType.ATTACK,CardRarity.SPECIAL,CardTarget.ENEMY,true);
        setDamage(6,2);
        setMagic(2);
        tags.add(EnchantmentsManager.Cantrip);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        Wiz.applyToEnemy(m,new VulnerablePower(m,magicNumber,false));
    }
}
