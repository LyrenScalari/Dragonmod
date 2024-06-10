package dragonmod.cards.Rimedancer.Common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LockOnPower;
import dragonmod.DragonMod;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.cards.Rimedancer.Uncommon.SleevedAce;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.Wiz;

public class Squall extends AbstractRimedancerCard {
    public static final String ID = Squall.class.getSimpleName();

    public Squall() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        setMagic(3,1);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToEnemy(m,new LockOnPower(m,magicNumber));
        if (DragonMod.getLeftCard(this) != null){
            Wiz.atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    AbstractCard target = DragonMod.getLeftCard(Squall.this);
                    target.freeToPlayOnce = true;
                    Wiz.Player().hand.removeCard(target);
                    EnchantmentsManager.addCard(target,true,Wiz.Player());
                }
            });
        }
        if (DragonMod.getRightCard(this) != null){
            Wiz.atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    AbstractCard target = DragonMod.getRightCard(Squall.this);
                    target.freeToPlayOnce = true;
                    Wiz.Player().hand.removeCard(target);
                    EnchantmentsManager.addCard(target,true,Wiz.Player());
                }
            });
        }
    }
}
