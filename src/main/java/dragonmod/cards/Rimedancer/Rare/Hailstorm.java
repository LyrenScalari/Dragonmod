package dragonmod.cards.Rimedancer.Rare;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.orbs.Icicle;
import dragonmod.powers.Rimedancer.HailstormPower;
import dragonmod.util.Wiz;

public class Hailstorm extends AbstractRimedancerCard {
    public static final String ID = Hailstorm.class.getSimpleName();
    public Hailstorm(){
        super(ID,-1,CardType.SKILL,CardRarity.RARE,CardTarget.SELF);
        setMagic(4,2);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int power = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            power = this.energyOnUse;
        }
        if (p.hasRelic("Chemical X")) {
            power += 2;
            p.getRelic("Chemical X").flash();
        }
        if (power > 0) {
            for(int i = 0; i < power; ++i) {
                Wiz.atb(new ChannelAction(new Icicle()));
            }
            Wiz.applyToSelfNextTurn(new HailstormPower(p,p,power));
            if (!this.freeToPlayOnce) {// 55
                p.energy.use(EnergyPanel.totalCount);
            }
        }
    }
}
