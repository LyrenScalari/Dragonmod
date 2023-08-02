package dragonmod.cards.Justicar;


import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.GainCustomBlockAction;
import com.evacipated.cardcrawl.mod.stslib.blockmods.BlockModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.AddIconToDescriptionMod;
import dragonmod.DamageModifiers.BlockModifiers.DivineBlock;
import dragonmod.DamageModifiers.Icons.LightIcon;

public class HolyWordBarrier extends AbstractHolyCard {

    public static final String ID = HolyWordBarrier.class.getSimpleName();
    public HolyWordBarrier() {
        super(ID,1,CardType.SKILL,CardRarity.COMMON,CardTarget.SELF);
        setBlock(7,2);
        BlockModifierManager.addModifier(this,new DivineBlock(true));
        CardModifierManager.addModifier(this,new AddIconToDescriptionMod(AddIconToDescriptionMod.BLOCK, LightIcon.get()));
    }

    // Actions the card should do.

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainCustomBlockAction(this, AbstractDungeon.player,block));
        super.use(p,m);
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(2);
            initializeDescription();
        }
    }
}
