package dragonmod.cards.Justicar.rare;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.util.Wiz;

public class SeraphForm extends AbstractJusticarCard {

    public static final String ID = SeraphForm.class.getSimpleName();
    public SeraphForm(){
        super(ID,3,CardType.POWER,CardRarity.RARE,CardTarget.SELF);
        this.tags.add(BaseModCardTags.FORM);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
            }
        });
    }
}

