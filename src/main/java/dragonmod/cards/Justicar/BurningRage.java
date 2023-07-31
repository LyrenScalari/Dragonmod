package dragonmod.cards.Justicar;


import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;

public class BurningRage extends AbstractHolyCard{

    public static final String ID = DragonMod.makeID(BurningRage.class.getSimpleName());
    public BurningRage() {
        super(ID,2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        setMagic(1);
        cardsToPreview = new ToClaimTheirBones();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        super.use(p,m);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            cardsToPreview.upgrade();
            super.upgrade();
        }
    }
}