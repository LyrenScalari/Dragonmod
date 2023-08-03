package dragonmod.cards.Rimedancer.Common;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FocusPower;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.powers.Rimedancer.IntensityPower;
import dragonmod.util.Wiz;

import static dragonmod.util.Wiz.atb;

public class ChillOut extends AbstractRimedancerCard {

    public static final String ID = ChillOut.class.getSimpleName();

    public ChillOut() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        setMagic(2);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelfTempstart(new FocusPower(p,magicNumber));
        atb(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new IntensityPower(p,magicNumber)));
    }
}