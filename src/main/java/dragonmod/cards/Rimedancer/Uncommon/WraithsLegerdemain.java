package dragonmod.cards.Rimedancer.Uncommon;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.util.Wiz;

public class WraithsLegerdemain extends AbstractRimedancerCard {
    public static final String ID = WraithsLegerdemain.class.getSimpleName();
    public WraithsLegerdemain(){
        super(ID,1,CardType.SKILL,CardRarity.UNCOMMON,CardTarget.NONE);
        setMagic(2,1);
        setMagic2(1,1);
    }
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        int tilt = 0;
        for (AbstractCard c : Wiz.Player().hand.group){
            if (Wiz.Player().hand.group.indexOf(c) > Wiz.Player().hand.group.indexOf(this)){
                tilt += 1;
            }
        }
        if (tilt >= 3){
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int tilt = 0;
        for (AbstractCard c : Wiz.Player().hand.group){
            if (Wiz.Player().hand.group.indexOf(c) > Wiz.Player().hand.group.indexOf(this)){
                tilt += 1;
            }
        }
        Wiz.atb(new DrawCardAction(magicNumber));
        if (tilt >= 3){
            Wiz.applyToSelf(new EnergizedPower(p,SecondMagicNumber));
            Wiz.applyToSelf(new DrawCardNextTurnPower(p, 1));
        }
    }
}
