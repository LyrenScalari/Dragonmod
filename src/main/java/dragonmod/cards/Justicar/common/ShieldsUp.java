package dragonmod.cards.Justicar.common;

import com.evacipated.cardcrawl.mod.stslib.blockmods.BlockModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DamageModifiers.BlockModifiers.DivineBlock;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.powers.general.CouragePower;
import dragonmod.util.Wiz;

public class ShieldsUp extends AbstractJusticarCard {

    public static final String ID = ShieldsUp.class.getSimpleName();
    public ShieldsUp(){
        super(ID,2,CardType.SKILL,CardRarity.COMMON,CardTarget.SELF);
        setBlock(12,2);
        setMagic(3,1);
        BlockModifierManager.addModifier(this,new DivineBlock(true));
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.block(p,block);
        Wiz.applyToSelf(new CouragePower(p,p,magicNumber));
    }
}
