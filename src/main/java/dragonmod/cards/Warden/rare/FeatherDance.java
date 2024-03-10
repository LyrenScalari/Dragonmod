package dragonmod.cards.Warden.rare;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.cards.Warden.AbstractReflexiveCard;
import dragonmod.cards.Warden.rare.amber.WaterfowlDance;
import dragonmod.cards.Warden.rare.amethyst.RavenousViper;
import dragonmod.cards.Warden.special.DawnStanceCard;
import dragonmod.cards.Warden.special.DuskStanceCard;
import dragonmod.powers.general.ReinforcePower;
import dragonmod.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public class FeatherDance extends AbstractReflexiveCard {

    public static final String ID = FeatherDance.class.getSimpleName();
    public FeatherDance(){
        super(ID,2,CardType.SKILL,CardRarity.RARE,CardTarget.SELF);
        setBlock(12,3);
        setMagic(3,1);
        setReflectivePairing(new WaterfowlDance(), new RavenousViper());
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
        Wiz.applyToSelf(new ReinforcePower(p,p,magicNumber));
        DragonMod.StanceChoices.clear();
        DragonMod.StanceChoices.add(new DawnStanceCard());
        DragonMod.StanceChoices.add(new DuskStanceCard());
        Wiz.atb(new ChooseOneAction(DragonMod.StanceChoices));
    }
}
