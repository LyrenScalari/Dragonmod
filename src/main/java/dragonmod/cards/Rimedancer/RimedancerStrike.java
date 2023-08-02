package dragonmod.cards.Rimedancer;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.util.Wiz;


public class RimedancerStrike extends AbstractRimedancerCard {
    public static final String ID = RimedancerStrike.class.getSimpleName();
    public RimedancerStrike(){
        super(ID,1,CardType.ATTACK,CardRarity.BASIC,CardTarget.ENEMY);
        setDamage(6,3);
        this.tags.add(AbstractCard.CardTags.STARTER_STRIKE);
        this.tags.add(AbstractCard.CardTags.STRIKE);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m,new DamageInfo(p,baseDamage, DamageInfo.DamageType.NORMAL));
    }
}