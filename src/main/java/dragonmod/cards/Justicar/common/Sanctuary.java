package dragonmod.cards.Justicar.common;

import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.SCVTemporalCardMod;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.interfaces.TurnStartEnchantment;
import dragonmod.powers.Dragonkin.ConfessionPower;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public class Sanctuary extends AbstractJusticarCard implements TurnStartEnchantment {

    public static final String ID = Sanctuary.class.getSimpleName();
    public Sanctuary(){
        super(ID,1,CardType.SKILL,CardRarity.COMMON,CardTarget.SELF);
        setMagic(3,2);
        energyCosts.put(TypeEnergyHelper.Mana.Charge,3);
        energyCosts.put(TypeEnergyHelper.Mana.BaseCharge,3);
        tags.add(EnchantmentsManager.Verse);
        CardModifierManager.addModifier(this,new SCVTemporalCardMod());
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
    }

    @Override
    public void EnchantedTurnStart(AbstractCreature owner) {
        Wiz.applyToSelf(new ConfessionPower(Wiz.Player(),magicNumber));
    }
}
