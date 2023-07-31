package dragonmod.cards.Justicar;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.orbs.ConscecrationSeal;

public class Condemnation extends AbstractHolyCard {

    public static final String ID = DragonMod.makeID(Condemnation.class.getSimpleName());
    public Condemnation() {
        super(ID, 2,CardType.ATTACK,CardRarity.RARE,CardTarget.SELF);
        setDamage(40,10);
        setMagic(30,-5);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                DragonMod.Seals.add(new ConscecrationSeal(damage,magicNumber));
                isDone = true;
            }
        });
        super.use(p,m);
    }
}