package dragonmod.cards.Rimedancer.Special;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Draconic.AbstractDraconicCard;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.Wiz;

public class Nightrunner extends AbstractDraconicCard {
    public static final String ID = Nightrunner.class.getSimpleName();
    public Nightrunner(){
        super(ID,0,CardType.SKILL,CardRarity.SPECIAL,CardTarget.SELF);
        setBlock(4,2);
        setExhaust(true);
        tags.add(EnchantmentsManager.Cantrip);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.block(p,block);
        Wiz.atb(new DrawCardAction(1));
    }
}