package dragonmod.cards.Justicar;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.actions.BurnAfterReadingFollowUpAction;


public class BurnAfterReading extends AbstractPrimalCard {
    public static final String ID = DragonMod.makeID(BurnAfterReading.class.getSimpleName());
    public BurnAfterReading() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        setMagic(2,2);
        exhaust = true;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(magicNumber,new BurnAfterReadingFollowUpAction()));
        super.use(p,m);
    }
}