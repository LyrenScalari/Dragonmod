package dragonmod.cards.Rimedancer.Starter;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.actions.FlourishAction;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.orbs.Icicle;
import dragonmod.util.Wiz;

public class HailstoneHaze extends AbstractRimedancerCard {
    public static final String ID = HailstoneHaze.class.getSimpleName();
    public HailstoneHaze(){
        super(ID,0,CardType.SKILL,CardRarity.BASIC,CardTarget.SELF);
        setMagic(1,1);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++){
            Wiz.atb(new ChannelAction(new Icicle()));
        }
        Wiz.atb(new FlourishAction());
    }
}