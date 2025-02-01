package dragonmod.cards.Rimedancer.Uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.actions.FireAction;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.orbs.Icicle;
import dragonmod.powers.Rimedancer.Chillpower;
import dragonmod.util.Wiz;

public class Crackle extends AbstractRimedancerCard {

    public static final String ID = Crackle.class.getSimpleName();

    public Crackle() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        setMagic(5,2);
    }
    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new FireAction(Icicle.class));
        Wiz.applyToEnemy(m,new Chillpower(m,p,magicNumber));
    }
}