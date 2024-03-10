package dragonmod.cards.Warden.starter;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.cards.Warden.AbstractReflexiveCard;
import dragonmod.cards.Warden.special.DawnStanceCard;
import dragonmod.cards.Warden.special.DuskStanceCard;
import dragonmod.cards.Warden.starter.amber.AmberHaze;
import dragonmod.cards.Warden.starter.amethyst.AmethystHaze;
import dragonmod.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public class ShroudedSky extends AbstractReflexiveCard {

    public static final String ID = ShroudedSky.class.getSimpleName();
    public ShroudedSky(){
        super(ID,0,CardType.SKILL,CardRarity.BASIC,CardTarget.SELF);
        setBlock(4,2);
        setReflectivePairing(new AmberHaze(), new AmethystHaze());
    }
    @Override
    public List<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> retVal = new ArrayList<>();
        retVal.add(new TooltipInfo(AmethystBlossomString.TEXT[0],AmethystBlossomString.TEXT[1]));
        retVal.add(new TooltipInfo(AmberBlossomString.TEXT[0],AmberBlossomString.TEXT[1]));
        return retVal;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.block(p,block);
        DragonMod.StanceChoices.clear();
        DragonMod.StanceChoices.add(new DawnStanceCard());
        DragonMod.StanceChoices.add(new DuskStanceCard());
        Wiz.atb(new ChooseOneAction(DragonMod.StanceChoices));
    }
}