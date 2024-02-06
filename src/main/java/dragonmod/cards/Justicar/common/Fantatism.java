package dragonmod.cards.Justicar.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.util.Wiz;

public class Fantatism extends AbstractJusticarCard {

    public static final String ID = Fantatism.class.getSimpleName();
    public Fantatism(){
        super(ID,0,CardType.SKILL,CardRarity.COMMON,CardTarget.SELF);
        setMagic(1,1);
        setMagic2(9);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(p,new DamageInfo(p,SecondMagicNumber, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        Wiz.atb(new GainEnergyAction(magicNumber));
        Wiz.atb(new DrawCardAction(magicNumber));
        Wiz.applyToSelf(new VulnerablePower(p,1,false));
    }
}