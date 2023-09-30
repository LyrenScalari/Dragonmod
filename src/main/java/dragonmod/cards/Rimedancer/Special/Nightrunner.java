package dragonmod.cards.Rimedancer.Special;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.util.CantripManager;
import dragonmod.util.Wiz;

public class Nightrunner extends AbstractRimedancerCard {
    public static final String ID = Nightrunner.class.getSimpleName();
    public Nightrunner(){
        super(ID,0,CardType.SKILL,CardRarity.SPECIAL,CardTarget.SELF,true);
        setBlock(4,2);
        setExhaust(true);
        tags.add(CantripManager.Cantrip);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.block(p,block);
        Wiz.atb(new DrawCardAction(1));
    }
}