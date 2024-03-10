package dragonmod.cards.Warden.uncommon;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.cards.Warden.AbstractReflexiveCard;
import dragonmod.cards.Warden.special.DawnStanceCard;
import dragonmod.cards.Warden.special.DuskStanceCard;
import dragonmod.cards.Warden.uncommon.amber.FieryDance;
import dragonmod.cards.Warden.uncommon.amethyst.ShadeDancer;
import dragonmod.powers.Warden.EmeraldBlossomPower;
import dragonmod.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public class WingsofHeaven extends AbstractReflexiveCard {

    public static final String ID = WingsofHeaven.class.getSimpleName();
    public WingsofHeaven(){
        super(ID,1,CardType.ATTACK,CardRarity.UNCOMMON,CardTarget.ENEMY);
        setDamage(8,4);
        setMagic(1);
        setReflectivePairing(new FieryDance(), new ShadeDancer());
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
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL));
        Wiz.applyToSelf(new EmeraldBlossomPower(1));
        DragonMod.StanceChoices.clear();
        DragonMod.StanceChoices.add(new DawnStanceCard());
        DragonMod.StanceChoices.add(new DuskStanceCard());
        Wiz.atb(new ChooseOneAction(DragonMod.StanceChoices));
    }
}
