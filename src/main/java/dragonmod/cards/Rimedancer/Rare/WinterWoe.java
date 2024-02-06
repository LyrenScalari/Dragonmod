package dragonmod.cards.Rimedancer.Rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.orbs.Icicle;
import dragonmod.powers.Rimedancer.Subzero;
import dragonmod.util.Wiz;

public class WinterWoe  extends AbstractRimedancerCard {
    public static final String ID = WinterWoe.class.getSimpleName();
    public WinterWoe(){
        super(ID,1,CardType.SKILL,CardRarity.RARE,CardTarget.SELF);
        setMagic(1,1);
        setExhaust(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractPower subzero = p.getPower(Subzero.POWER_ID);
        if (subzero != null){
            this.addToTop(new ApplyPowerAction(p, p, new Subzero(subzero.amount*magicNumber), subzero.amount*magicNumber));
        }
        Wiz.atb(new ChannelAction(new Icicle()));
    }
}