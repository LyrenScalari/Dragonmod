package dragonmod.cards.Rimedancer;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.orbs.Icicle;
import dragonmod.util.Wiz;

public class Nothingbutcloak extends AbstractRimedancerCard {
    public static final String ID = DragonMod.makeID(Nothingbutcloak.class.getSimpleName());
   public Nothingbutcloak(){
        super(ID,1,CardType.SKILL,CardRarity.COMMON,CardTarget.SELF);
        setBlock(6);
        setMagic(1,1);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.block(p,block);
        for (int i = 0; i < magicNumber; i++){
            Wiz.atb(new ChannelAction(new Icicle()));
        }
    }
}