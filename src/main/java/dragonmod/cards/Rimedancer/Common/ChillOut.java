package dragonmod.cards.Rimedancer.Common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.actions.FlourishAction;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.util.Wiz;

public class ChillOut extends AbstractRimedancerCard {

    public static final String ID = ChillOut.class.getSimpleName();

    public ChillOut() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        setMagic(3,-1);
    }
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        int tilt = 0;
        for (AbstractCard c : Wiz.Player().hand.group){
            if (Wiz.Player().hand.group.indexOf(c) > Wiz.Player().hand.group.indexOf(this)){
                tilt += 1;
            }
        }
        if (tilt >= magicNumber){
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new FlourishAction());
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                AbstractCard glimpse = p.hand.getBottomCard();
                p.hand.removeCard(glimpse);
                p.hand.addToTop(glimpse);
            }
        });
        int tilt = 0;
        for (AbstractCard c : Wiz.Player().hand.group){
            if (Wiz.Player().hand.group.indexOf(c) > Wiz.Player().hand.group.indexOf(ChillOut.this)){
                tilt += 1;
            }
        }
        if (tilt >= magicNumber){
            Wiz.atb(new DrawCardAction(1));
        }
    }
}