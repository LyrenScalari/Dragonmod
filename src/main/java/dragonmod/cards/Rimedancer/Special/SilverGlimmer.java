package dragonmod.cards.Rimedancer.Special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import dragonmod.actions.FireAction;
import dragonmod.cards.Draconic.AbstractDraconicCard;
import dragonmod.orbs.Icicle;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.Wiz;

public class SilverGlimmer extends AbstractDraconicCard {
    public static final String ID = SilverGlimmer.class.getSimpleName();
    public SilverGlimmer(){
        super(ID,1,CardType.SKILL,CardRarity.SPECIAL,CardTarget.ENEMY);
        setMagic(1,1);
        tags.add(EnchantmentsManager.Cantrip);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new FireAction(Icicle.class));
        Wiz.applyToEnemy(m,new WeakPower(m,magicNumber,false));
    }
}