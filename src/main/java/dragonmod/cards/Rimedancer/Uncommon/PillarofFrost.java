package dragonmod.cards.Rimedancer.Uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FocusPower;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.powers.Rimedancer.IntensityPower;
import dragonmod.powers.Rimedancer.Subzero;
import dragonmod.util.Wiz;

public class PillarofFrost extends AbstractRimedancerCard {
    public static final String ID = PillarofFrost.class.getSimpleName();

    public PillarofFrost() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        setMagic(8,2);
        setMagic2(5,1);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new FocusPower(p,SecondMagicNumber));
        Wiz.applyToSelf(new IntensityPower(SecondMagicNumber,2));
        Wiz.applyToSelf(new Subzero(magicNumber));
    }
}
