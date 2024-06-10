package dragonmod.cards.Rimedancer.Uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.actions.FlourishAction;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.Wiz;

public class SleevedAce extends AbstractRimedancerCard {
    public static final String ID = SleevedAce.class.getSimpleName();

    public SleevedAce() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new FlourishAction());
        if (DragonMod.getLeftCard(this) != null){
            Wiz.atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    AbstractCard target = DragonMod.getLeftCard(SleevedAce.this);
                    target.freeToPlayOnce = true;
                    Wiz.Player().hand.removeCard(target);
                    EnchantmentsManager.addCard(target,true,Wiz.Player());
                }
            });
        }
    }
}
