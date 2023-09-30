package dragonmod.cards.Justicar.uncommon;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.powers.general.ReinforcePower;
import dragonmod.util.Wiz;

public class RepairArmor extends AbstractJusticarCard {

    public static final String ID = RepairArmor.class.getSimpleName();
    public RepairArmor(){
        super(ID,0,CardType.SKILL,CardRarity.UNCOMMON,CardTarget.SELF);
        setBlock(4,2);
        setMagic(1);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.block(p,block);
        Wiz.atb(new RemoveSpecificPowerAction(p,p, FrailPower.POWER_ID));
        Wiz.applyToSelf(new ReinforcePower(p,p,magicNumber));
    }
}
