package dragonmod.cards.Justicar;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.orbs.WrathSeal;


public class BlessingofWrath extends AbstractHolyCard {

    public static final String ID = DragonMod.makeID(BlessingofWrath.class.getSimpleName());

    public BlessingofWrath() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        setDamage(10,3);
        setMagic(4);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                DragonMod.Seals.add(new WrathSeal(damage,magicNumber));
                isDone = true;
            }
        });
        super.use(p,m);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(3);
            initializeDescription();
        }
    }
}