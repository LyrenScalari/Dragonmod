package dragonmod.cards.Rimedancer.Uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import dragonmod.actions.ExploitAction;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.util.Wiz;

public class DeathlyDispute extends AbstractRimedancerCard {
    public static final String ID = DeathlyDispute.class.getSimpleName();
    public DeathlyDispute(){
        super(ID,1,CardType.ATTACK,CardRarity.UNCOMMON,CardTarget.ENEMY);
        setDamage(8,4);
        setMagic(2,1);
        setMagic2(5,5);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL));
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                AbstractCard glimpse = p.hand.getBottomCard();
                p.hand.removeCard(glimpse);
                p.hand.addToTop(glimpse);
                glimpse = p.hand.getBottomCard();
                p.hand.removeCard(glimpse);
                p.hand.addToTop(glimpse);
            }
        });
        Wiz.atb(new ExploitAction(()->new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                Wiz.vfx(new RainingGoldEffect(SecondMagicNumber,true));
                Wiz.atb(new GainGoldAction(SecondMagicNumber));
                Wiz.atb(new ExhaustSpecificCardAction(DeathlyDispute.this,p.discardPile));
            }
        },magicNumber,m));
    }
}
