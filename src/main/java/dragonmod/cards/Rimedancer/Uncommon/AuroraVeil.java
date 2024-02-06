package dragonmod.cards.Rimedancer.Uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.util.Wiz;

public class AuroraVeil extends AbstractRimedancerCard {

    public static final String ID = AuroraVeil.class.getSimpleName();
    public AuroraVeil(){
        super(ID,2,CardType.SKILL,CardRarity.UNCOMMON,CardTarget.ALL);
        setBlock(12,2);
        setMagic(1,1);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.block(p,block);
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters){
            Wiz.applyToEnemy(mo,new WeakPower(mo,magicNumber,false));
        }
    }
}
