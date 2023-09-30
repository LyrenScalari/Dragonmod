package dragonmod.cards.Justicar.starter;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.util.Wiz;

public class JusticarDefend extends AbstractJusticarCard {

    public static final String ID = JusticarDefend.class.getSimpleName();
    public JusticarDefend(){
        super(ID,1,CardType.SKILL,CardRarity.BASIC,CardTarget.SELF);
        setBlock(5,3);
        this.tags.add(AbstractCard.CardTags.STARTER_DEFEND);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.block(p,block);
    }
}
