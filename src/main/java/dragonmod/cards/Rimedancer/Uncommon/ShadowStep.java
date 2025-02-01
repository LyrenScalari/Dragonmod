package dragonmod.cards.Rimedancer.Uncommon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.util.Wiz;

public class ShadowStep extends AbstractRimedancerCard {
    public static final String ID = ShadowStep.class.getSimpleName();
    public ShadowStep(){
        super(ID,1,CardType.SKILL,CardRarity.UNCOMMON,CardTarget.NONE);
        setBlock(6,3);
        setMagic(5,-1);
    }
    public void applyPowers() {
        int realBaseBlock = baseBlock;
        int tilt = 0;
        for (AbstractCard c : Wiz.Player().hand.group){
            if (Wiz.Player().hand.group.indexOf(c) > Wiz.Player().hand.group.indexOf(this)){
                tilt += 1;
            }
        }
        baseBlock += tilt*2;
        super.applyPowers();
        this.baseBlock = realBaseBlock;
        this.isBlockModified = this.block != this.baseBlock;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int tilt = 0;
        for (AbstractCard c : Wiz.Player().hand.group){
            if (Wiz.Player().hand.group.indexOf(c) > Wiz.Player().hand.group.indexOf(this)){
                tilt += 1;
            }
        }
        Wiz.block(p,block+(tilt*2));
    }
}
