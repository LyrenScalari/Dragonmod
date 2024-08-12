package dragonmod.cards.Warden.uncommon;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Warden.AbstractWardenCard;

import java.util.ArrayList;
import java.util.List;

public class Ninjustu extends AbstractWardenCard {
    public static final String ID = Ninjustu.class.getSimpleName();
    public Ninjustu(){
        super(ID,2,CardType.POWER,CardRarity.UNCOMMON,CardTarget.SELF);
        setInnate(false,true);
        setMagic(2,2);
    }
    @Override
    public List<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> retVal = new ArrayList<>();
        retVal.add(new TooltipInfo(AmberBlossomString.TEXT[0],AmberBlossomString.TEXT[1]));
        return retVal;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }
}