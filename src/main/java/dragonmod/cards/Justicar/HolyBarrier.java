package dragonmod.cards.Justicar;


import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.GainCustomBlockAction;
import com.evacipated.cardcrawl.mod.stslib.blockmods.BlockModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import dragonmod.CardMods.AddIconToDescriptionMod;
import dragonmod.DamageModifiers.BlockModifiers.DivineBlock;
import dragonmod.DamageModifiers.Icons.LightIcon;
import dragonmod.DragonMod;

public class HolyBarrier extends AbstractHolyCard {

    public static final String ID = DragonMod.makeID(HolyBarrier.class.getSimpleName());

    public static int repeats = 0;
    public HolyBarrier() {
        super(ID,-1,CardType.SKILL,CardRarity.UNCOMMON,CardTarget.SELF);
        setBlock(5,2);
        setExhaust(true);
        setMagic(1);
        BlockModifierManager.addModifier(this,new DivineBlock(true));
        CardModifierManager.addModifier(this,new AddIconToDescriptionMod(AddIconToDescriptionMod.BLOCK, LightIcon.get()));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (AbstractDungeon.player.hasRelic("Chemical X")) {
            repeats += 2;
            AbstractDungeon.player.getRelic("Chemical X").flash();
        }
        repeats += EnergyPanel.totalCount;
        for (int i = 0; i < this.magicNumber + repeats; ++i) {
            addToBot(new GainCustomBlockAction(this,p,block));
        }
        super.use(p,m);
        EnergyPanel.useEnergy(EnergyPanel.totalCount);
        repeats = 0;
    }
}