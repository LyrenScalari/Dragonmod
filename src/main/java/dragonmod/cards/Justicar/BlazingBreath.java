package dragonmod.cards.Justicar;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.powers.Dragonkin.Scorchpower;

public class BlazingBreath extends AbstractPrimalCard {
    public static final String ID = BlazingBreath.class.getSimpleName();
    public BlazingBreath() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        setDamage(7,3);
        setMagic(4,1);
        setMagic2(1);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
        addToBot(new ApplyPowerAction(m,p,new Scorchpower(m,p,magicNumber)));
        addToBot(new DiscardAction(p,p,SecondMagicNumber,false,false));
        super.use(p,m);
    }
}
