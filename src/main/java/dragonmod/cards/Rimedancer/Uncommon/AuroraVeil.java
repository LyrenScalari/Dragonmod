package dragonmod.cards.Rimedancer.Uncommon;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.blockmods.BlockModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import dragonmod.CardMods.AddIconToDescriptionMod;
import dragonmod.DamageModifiers.BlockModifiers.IceArmor;
import dragonmod.DamageModifiers.Icons.FrostIcon;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.util.Wiz;

public class AuroraVeil extends AbstractRimedancerCard {

    public static final String ID = AuroraVeil.class.getSimpleName();
    public AuroraVeil(){
        super(ID,2,CardType.SKILL,CardRarity.UNCOMMON,CardTarget.ALL);
        setBlock(12,2);
        setMagic(1,1);
        BlockModifierManager.addModifier(this,new IceArmor(true));
        CardModifierManager.addModifier(this,new AddIconToDescriptionMod(AddIconToDescriptionMod.BLOCK, FrostIcon.get()));
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.block(p,block);
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters){
            Wiz.applyToEnemy(mo,new WeakPower(mo,magicNumber,false));
        }
    }
}
