package dragonmod.cards.Rimedancer;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import dragonmod.orbs.Sleet;
import dragonmod.util.Wiz;


public class Snowblind extends AbstractRimedancerCard {

    public static final String ID = Snowblind.class.getSimpleName();
    public Snowblind(){
        super(ID,1,CardType.SKILL,CardRarity.COMMON,CardTarget.ENEMY);
        setMagic(2);
        setMagic2(1,1);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToEnemy(m,new WeakPower(m,magicUpgrade,false));
        for (int i = 0; i < magicNumber; i++){
            Wiz.atb(new ChannelAction(new Sleet()));
        }
    }
}