package dragonmod.cards.Justicar.common;

import com.evacipated.cardcrawl.mod.stslib.actions.common.DamageCallbackAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.powers.general.ParryPower;
import dragonmod.powers.general.ReinforcePower;
import dragonmod.util.Wiz;

public class SurgingStrike extends AbstractJusticarCard {
    public static final String ID = SurgingStrike.class.getSimpleName();
    public SurgingStrike(){
        super(ID,1,CardType.ATTACK,CardRarity.COMMON,CardTarget.ENEMY);
        setDamage(9,2);
        setMagic(2);
        tags.add(CardTags.STRIKE);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL));
        Wiz.atb(new DamageCallbackAction(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY,(dmg)->{
            Wiz.applyToSelf(new ParryPower(p,p,dmg));
        }));
        Wiz.applyToSelf(new ReinforcePower(p,p,magicNumber));
    }
}
