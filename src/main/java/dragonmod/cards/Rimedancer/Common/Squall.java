package dragonmod.cards.Rimedancer.Common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LockOnPower;
import dragonmod.DragonMod;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
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
        AbstractCard left = DragonMod.getLeftCard(this);
        AbstractCard right = DragonMod.getRightCard(this);
        if (left != null){
            Wiz.atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    left.freeToPlayOnce = true;
                    Wiz.Player().hand.removeCard(left);
                    left.tags.add(EnchantmentsManager.Sleeved);
                    EnchantmentsManager.addCard(left,true,Wiz.Player());
                }
            });
        }
        if (right != null){
            Wiz.atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    right.freeToPlayOnce = true;
                    Wiz.Player().hand.removeCard(right);
                    right.tags.add(EnchantmentsManager.Sleeved);
                    EnchantmentsManager.addCard(right,true,Wiz.Player());
                }
            });
        }
    }
}
