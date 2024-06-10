package dragonmod.cards.Rimedancer.Common;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.blockmods.BlockModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.AddIconToDescriptionMod;
import dragonmod.DamageModifiers.BlockModifiers.IceArmor;
import dragonmod.DamageModifiers.Icons.FrostIcon;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.util.Wiz;

public class SnowFort extends AbstractRimedancerCard {

    public static final String ID = SnowFort.class.getSimpleName();
    public SnowFort(){
        super(ID,0,CardType.SKILL,CardRarity.COMMON,CardTarget.SELF);
        setBlock(3,2);
        BlockModifierManager.addModifier(this,new IceArmor(true));
        CardModifierManager.addModifier(this,new AddIconToDescriptionMod(AddIconToDescriptionMod.BLOCK, FrostIcon.get()));
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.block(p,block);
    }
}
