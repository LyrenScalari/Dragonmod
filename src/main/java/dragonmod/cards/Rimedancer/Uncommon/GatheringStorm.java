package dragonmod.cards.Rimedancer.Uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.actions.GainHailOrbSlotAction;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.powers.Rimedancer.Subzero;
import dragonmod.util.Wiz;

public class GatheringStorm extends AbstractRimedancerCard {
    public static final String ID = GatheringStorm.class.getSimpleName();

    public GatheringStorm() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        setBlock(7,3);
        setMagic(1,1);
        setMagic2(3,2);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.block(p,block);
        Wiz.applyToSelf(new Subzero(m,m,SecondMagicNumber));
        Wiz.atb(new GainHailOrbSlotAction(magicNumber));
    }
}