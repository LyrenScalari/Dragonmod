package dragonmod.cards.Rimedancer.Uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.cards.Rimedancer.Special.PilferedCrystal;

public class DeathlyDispute extends AbstractRimedancerCard {
    public static final String ID = DeathlyDispute.class.getSimpleName();
    public DeathlyDispute(){
        super(ID,1,CardType.ATTACK,CardRarity.UNCOMMON,CardTarget.SELF);
        setDamage(8,4);
        cardsToPreview = new PilferedCrystal();
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }
    @Override
    public void upgrade() {
        if (!upgraded) {
            cardsToPreview.upgrade();
            super.upgrade();
        }
    }
}
