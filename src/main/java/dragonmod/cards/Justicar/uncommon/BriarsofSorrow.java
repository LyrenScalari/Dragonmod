package dragonmod.cards.Justicar.uncommon;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.powers.Dragonkin.ConfessionPower;
import dragonmod.util.Wiz;

public class BriarsofSorrow extends AbstractJusticarCard {
    public static final String ID = BriarsofSorrow.class.getSimpleName();
    public BriarsofSorrow(){
        super(ID,0,CardType.ATTACK,CardRarity.UNCOMMON,CardTarget.ENEMY);
        setDamage(7,2);
        setMagic(4,2);
        setMagic2(5,-1);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL));
        Wiz.dmg(p,new DamageInfo(p,SecondMagicNumber, DamageInfo.DamageType.NORMAL));
        Wiz.applyToSelf(new ConfessionPower(p,magicNumber));
    }
}
