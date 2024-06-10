package dragonmod.cards.Justicar.starter;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.powers.Dragonkin.ConfessionPower;
import dragonmod.util.Wiz;

public class Sunbeam extends AbstractJusticarCard {
    public static final String ID = Sunbeam.class.getSimpleName();
    public Sunbeam(){
        super(ID,1,CardType.ATTACK,CardRarity.BASIC,CardTarget.ENEMY);
        setDamage(7,3);
        setMagic(7,3);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL));
        Wiz.applyToSelf(new ConfessionPower(Wiz.Player(),magicNumber));
    }
}
