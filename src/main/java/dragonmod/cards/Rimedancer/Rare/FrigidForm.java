package dragonmod.cards.Rimedancer.Rare;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.util.Wiz;

public class FrigidForm extends AbstractRimedancerCard {
    public static final String ID = FrigidForm.class.getSimpleName();
    public FrigidForm(){
        super(ID,3,CardType.POWER,CardRarity.RARE,CardTarget.SELF);
        setMagic(4,2);
        setMagic2(2,1);
        tags.add(BaseModCardTags.FORM);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new IncreaseMaxOrbAction(SecondMagicNumber));
        Wiz.applyToSelf(new dragonmod.powers.Rimedancer.FrigidForm(magicNumber));
    }
}
