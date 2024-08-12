package dragonmod.cards.Warden.rare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Warden.AbstractWardenCard;

public class BlackCoffin extends AbstractWardenCard {
    public static final String ID = BlackCoffin.class.getSimpleName();

    public BlackCoffin() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.ALL_ENEMY);
        setSelfRetain(false,true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }
}
