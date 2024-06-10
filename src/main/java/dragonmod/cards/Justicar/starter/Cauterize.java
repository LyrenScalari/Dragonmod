package dragonmod.cards.Justicar.starter;

import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.CardMods.SCVTemporalCardMod;
import dragonmod.DragonMod;
import dragonmod.actions.CureAction;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.interfaces.TurnStartEnchantment;
import dragonmod.powers.Dragonkin.ZealPower;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public class Cauterize extends AbstractJusticarCard implements TurnStartEnchantment {
    public static final String ID = Cauterize.class.getSimpleName();
    public Cauterize(){
        super(ID,0,CardType.SKILL,CardRarity.BASIC,CardTarget.SELF);
        setCustomVar("H",5,3);
        setBlock(5,3);
        setMagic(2);
        setVarCalculation("H", (m, base) -> {
            AbstractPower p = null;
            if (AbstractDungeon.player != null){
                p = AbstractDungeon.player.getPower(ZealPower.POWER_ID);
            }
            if(p != null){
                return base + p.amount;
            } else return base;
        });
        energyCosts.put(TypeEnergyHelper.Mana.Charge,2);
        energyCosts.put(TypeEnergyHelper.Mana.BaseCharge,2);
        CardModifierManager.addModifier(this,new SCVTemporalCardMod());
        tags.add(EnchantmentsManager.Verse);
    }
    @Override
    public List<String> getCardDescriptors() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(verseString.TEXT[0]);
        return retVal;
    }

    @Override
    public List<TooltipInfo> getCustomTooltipsTop() {
        ArrayList<TooltipInfo> retVal = new ArrayList<>();
        retVal.add(new TooltipInfo(verseString.TEXT[0],verseString.TEXT[1]));
        return retVal;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.block(p,block);
        AbstractCard neighbor = DragonMod.getLeftCard(this);
        Wiz.atb(new DiscardSpecificCardAction(neighbor));
    }

    @Override
    public void EnchantedTurnStart(AbstractCreature owner) {
        if (energyCosts.get(TypeEnergyHelper.Mana.Charge) <= 1){
            Wiz.atb(new CureAction(customVar( "H")));
        }
    }
}
