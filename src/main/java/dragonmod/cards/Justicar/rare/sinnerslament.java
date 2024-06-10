package dragonmod.cards.Justicar.rare;

import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.SCVTemporalCardMod;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.interfaces.TurnStartEnchantment;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public class sinnerslament extends AbstractJusticarCard implements TurnStartEnchantment {

    public static final String ID = sinnerslament.class.getSimpleName();
    public sinnerslament(){
        super(ID,3,CardType.SKILL,CardRarity.RARE,CardTarget.SELF);
        setMagic(18,-2);
        energyCosts.put(TypeEnergyHelper.Mana.Charge,6);
        energyCosts.put(TypeEnergyHelper.Mana.BaseCharge,6);
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
        Wiz.atb(new DamageAllEnemiesAction(Wiz.Player(),EnchantmentsManager.getDevotion(), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
        if (energyCosts.get(TypeEnergyHelper.Mana.Charge) <= 1){
            Wiz.atb(new LoseHPAction(Wiz.Player(),Wiz.Player(),magicNumber));
        }
    }
}
