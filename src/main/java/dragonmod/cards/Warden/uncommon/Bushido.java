package dragonmod.cards.Warden.uncommon;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Warden.AbstractWardenCard;

import java.util.ArrayList;
import java.util.List;

public class Bushido extends AbstractWardenCard {
    public static final String ID = Bushido.class.getSimpleName();
    public Bushido(){
        super(ID,2,CardType.POWER,CardRarity.UNCOMMON,CardTarget.SELF);
        setSelfRetain(false,true);
        setMagic(1,1);
    }
    @Override
    public List<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> retVal = new ArrayList<>();
        retVal.add(new TooltipInfo(AmethystBlossomString.TEXT[0],AmethystBlossomString.TEXT[1]));
        return retVal;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }
}
