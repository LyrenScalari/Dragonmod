package dragonmod.cards.Rimedancer.Starter;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.util.Wiz;


public class RimedancerDefend extends AbstractRimedancerCard {

    public static final String ID = RimedancerDefend.class.getSimpleName();
    public RimedancerDefend(){
        super(ID,1,CardType.SKILL,CardRarity.BASIC,CardTarget.SELF);
        setBlock(5,3);
        this.tags.add(AbstractCard.CardTags.STARTER_DEFEND);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.block(p,block);
    }
}