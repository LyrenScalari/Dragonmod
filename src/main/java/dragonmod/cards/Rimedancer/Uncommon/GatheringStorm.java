package dragonmod.cards.Rimedancer.Uncommon;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.blockmods.BlockModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.AddIconToDescriptionMod;
import dragonmod.DamageModifiers.BlockModifiers.IceArmor;
import dragonmod.DamageModifiers.Icons.FrostIcon;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.powers.Rimedancer.Subzero;
import dragonmod.util.Wiz;

public class GatheringStorm extends AbstractRimedancerCard {
    public static final String ID = GatheringStorm.class.getSimpleName();

    public GatheringStorm() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        setBlock(7,3);
        setMagic(1,1);
        setMagic2(3,2);
        BlockModifierManager.addModifier(this,new IceArmor(true));
        CardModifierManager.addModifier(this,new AddIconToDescriptionMod(AddIconToDescriptionMod.BLOCK, FrostIcon.get()));
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.block(p,block);
        Wiz.applyToSelf(new Subzero(SecondMagicNumber));
    }
}