package dragonmod.cards.Rimedancer.Common;

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
        setMagic(4,-1);
        setBlock(7,2);
    }
    public void triggerOnGlowCheck() {

        this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
    }
    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new FlourishAction());
        Wiz.block(p,block);
    }
}