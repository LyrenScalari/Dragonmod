package dragonmod.cards.Rimedancer.Common;

import com.evacipated.cardcrawl.mod.stslib.blockmods.BlockModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DamageModifiers.BlockModifiers.IceArmor;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.util.Wiz;

public class SnowFort extends AbstractRimedancerCard {

    public static final String ID = SnowFort.class.getSimpleName();
    public SnowFort(){
        super(ID,0,CardType.SKILL,CardRarity.COMMON,CardTarget.SELF);
        setBlock(3,2);
        BlockModifierManager.addModifier(this,new IceArmor(true));
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.block(p,block);
    }
}
