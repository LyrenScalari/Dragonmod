package dragonmod.cards.Rimedancer.Rare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.powers.Rimedancer.powercards.ArcaneCurtainPower;
import dragonmod.util.Wiz;

public class ArcaneCurtain extends AbstractRimedancerCard {
    public static final String ID = ArcaneCurtain.class.getSimpleName();
    public ArcaneCurtain() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        setInnate(false,true);
        setMagic(1);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new ArcaneCurtainPower(p,magicNumber));
    }
}
