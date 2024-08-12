package dragonmod.cards.Rimedancer.Special;

import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.SoulboundField;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Draconic.AbstractDraconicCard;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public class Nightrunner extends AbstractDraconicCard {
    public static final String ID = Nightrunner.class.getSimpleName();
    public Nightrunner(){
        super(ID,0,CardType.SKILL,CardRarity.SPECIAL,CardTarget.SELF);
        setBlock(4,2);
        setExhaust(true);
        tags.add(EnchantmentsManager.Cantrip);
        SoulboundField.soulbound.set(this,true);
    }
    @Override
    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo> retVal = new ArrayList<>();
        retVal.add(new TooltipInfo(CantripTooltip.TEXT[0], CantripTooltip.TEXT[1]));
        return retVal;
    }
    @Override
    public List<String> getCardDescriptors() {
        ArrayList<String> retval = new ArrayList<>();
        retval.add(CantripTooltip.TEXT[0]);
        return retval;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.block(p,block);
        Wiz.atb(new DrawCardAction(1));
    }
}