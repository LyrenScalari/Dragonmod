package dragonmod.cards.Rimedancer.Uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.powers.Rimedancer.IcyVeinsPower;
import dragonmod.util.Wiz;

public class IcyVeins extends AbstractRimedancerCard {
    public static final String ID = IcyVeins.class.getSimpleName();
    public IcyVeins(){
        super(ID,2,CardType.POWER,CardRarity.UNCOMMON,CardTarget.SELF);
        setMagic(2);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new FocusPower(p,magicNumber));
        Wiz.applyToSelf(new StrengthPower(p,magicNumber));
        Wiz.applyToSelf(new IcyVeinsPower(magicNumber));
    }
}
