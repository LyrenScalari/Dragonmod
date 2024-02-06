package dragonmod.cards.Rimedancer.Uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import dragonmod.actions.GainHailOrbSlotAction;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.util.Wiz;

public class StreetRink extends AbstractRimedancerCard {
    public static final String ID = StreetRink.class.getSimpleName();

    public StreetRink() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        setMagic(2);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new GainHailOrbSlotAction(1));
        if (!upgraded){
            Wiz.applyToEnemy(m,new VulnerablePower(m,magicNumber,false));
        } else {
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters){
                Wiz.applyToEnemy(mo,new VulnerablePower(mo,magicNumber,false));
            }
        }
    }
    @Override
    public void upgrade() {
        super.upgrade();
        if (!upgraded) {
            upgradeName();
            target = CardTarget.ALL_ENEMY;
        }

    }
}
