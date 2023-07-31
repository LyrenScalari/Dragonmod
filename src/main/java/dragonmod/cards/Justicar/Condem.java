package dragonmod.cards.Justicar;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.powers.Dragonkin.PenancePower;


public class Condem extends AbstractHolyCard {


    // TEXT DECLARATION

    public static final String ID = DragonMod.makeID(Condem.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public Condem() {
        super(ID,1,CardType.SKILL,CardRarity.COMMON,CardTarget.ENEMY);
        setMagic(5,2);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m,p,new PenancePower(m,p,magicNumber)));
        super.use(p,m);
    }
}