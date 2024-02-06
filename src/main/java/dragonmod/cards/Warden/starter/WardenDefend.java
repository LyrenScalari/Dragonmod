package dragonmod.cards.Warden.starter;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Warden.AbstractWardenCard;
import dragonmod.util.Wiz;

public class WardenDefend extends AbstractWardenCard {

    public static final String ID = WardenDefend.class.getSimpleName();
    public WardenDefend(){
        super(ID,1,CardType.SKILL,CardRarity.BASIC,CardTarget.SELF);
        setBlock(5,3);
        this.tags.add(AbstractCard.CardTags.STARTER_DEFEND);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.block(p,block);
    }
}
