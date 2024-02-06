package dragonmod.cards.Rimedancer.Common;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.util.Wiz;

public class LeadthePrey extends AbstractRimedancerCard {
    public static final String ID = LeadthePrey.class.getSimpleName();
    public LeadthePrey(){
        super(ID,1,CardType.SKILL,CardRarity.COMMON,CardTarget.SELF);
        setMagic(2,1);
        setMagic2(5,3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelfTempstart(new FocusPower(p,magicNumber));
        Wiz.applyToSelf(new VigorPower(p,SecondMagicNumber));
    }
}