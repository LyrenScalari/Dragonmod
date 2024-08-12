package dragonmod.cards.Justicar.uncommon;

import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.SCVTemporalCardMod;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.interfaces.TurnStartEnchantment;
import dragonmod.powers.general.ParryPower;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public class RudeInterlude extends AbstractJusticarCard implements TurnStartEnchantment {

    public static final String ID = RudeInterlude.class.getSimpleName();
    public RudeInterlude(){
        super(ID,1,CardType.SKILL,CardRarity.UNCOMMON,CardTarget.SELF);
        energyCosts.put(TypeEnergyHelper.Mana.Charge,1);
        energyCosts.put(TypeEnergyHelper.Mana.BaseCharge,1);
        CardModifierManager.addModifier(this,new SCVTemporalCardMod());
        setMagic(12,2);
        setMagic2(8,-2);
        tags.add(EnchantmentsManager.Verse);;
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
        Wiz.applyToSelf(new ParryPower(p,p,magicNumber));
    }

    @Override
    public void EnchantedTurnStart(AbstractCreature owner) {
        if (energyCosts.get(TypeEnergyHelper.Mana.Charge) <= 1){
            Wiz.atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                }
            });
        }
    }
}