package dragonmod.cards.Rimedancer.Uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FocusPower;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.powers.Rimedancer.Subzero;
import dragonmod.util.Wiz;

public class PillarofFrost extends AbstractRimedancerCard {
    public static final String ID = PillarofFrost.class.getSimpleName();

    public PillarofFrost() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        setBlock(12,3);
        setMagic(4,1);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.block(p,block);
        Wiz.applyToSelfTempstart(new FocusPower(p,magicNumber));
        Wiz.applyToSelf(new Subzero(p,p,magicNumber));
    }
}
