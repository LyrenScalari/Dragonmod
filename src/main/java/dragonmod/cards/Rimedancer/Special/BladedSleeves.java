package dragonmod.cards.Rimedancer.Special;

import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.SoulboundField;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Draconic.AbstractDraconicCard;
import dragonmod.orbs.Icicle;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public class BladedSleeves extends AbstractDraconicCard {
        public static final String ID = BladedSleeves.class.getSimpleName();
        public BladedSleeves(){
            super(ID,0,CardType.ATTACK,CardRarity.SPECIAL,CardTarget.ENEMY);
            setMagic(1,1);
            setDamage(4,2);
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
            Wiz.atb(new ChannelAction(new Icicle()));
            Wiz.atb(new DamageAction(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL)));
        }
}
