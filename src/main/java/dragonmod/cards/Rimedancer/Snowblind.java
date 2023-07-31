package dragonmod.cards.Rimedancer;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import dragonmod.DragonMod;
import dragonmod.orbs.Icicle;
import dragonmod.util.Wiz;


public class Snowblind extends AbstractRimedancerCard {

    public static final String ID = DragonMod.makeID(Snowblind.class.getSimpleName());
    public Snowblind(){
        super(ID,1,CardType.SKILL,CardRarity.COMMON,CardTarget.ENEMY);
        magicNumber = baseMagicNumber = 2;
        SecondMagicNumber = BaseSecondMagicNumber = 1;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToEnemy(m,new WeakPower(m,magicUpgrade,false));
        for (int i = 0; i < magicNumber; i++){
            Wiz.atb(new ChannelAction(new Icicle()));
        }
    }
}