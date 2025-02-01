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
        setMagic2(2,1);
        setExhaust(true);
    }
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DrawCardAction(magicNumber));
        Wiz.applyToSelf(new EnergizedPower(p,SecondMagicNumber));
        Wiz.applyToSelf(new DrawCardNextTurnPower(p, SecondMagicNumber));
    }
}
