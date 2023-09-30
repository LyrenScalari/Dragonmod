package dragonmod.cards.Justicar.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.util.Wiz;

public class Fantatism extends AbstractJusticarCard {

    public static final String ID = Fantatism.class.getSimpleName();
    public Fantatism(){
        super(ID,0,CardType.SKILL,CardRarity.COMMON,CardTarget.SELF);
        setMagic(5);
        setMagic2(2,1);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(p,new DamageInfo(p,magicNumber, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
    }
}