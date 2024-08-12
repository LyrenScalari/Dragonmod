package dragonmod.cards.Justicar.common;

import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import dragonmod.CardMods.SCVTemporalCardMod;
import dragonmod.actions.CureAction;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.interfaces.OnOverheal;
import dragonmod.interfaces.TurnStartEnchantment;
import dragonmod.powers.Dragonkin.InspirationPower;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.StigmataManager;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public class PrayerofVigor extends AbstractJusticarCard implements TurnStartEnchantment, OnOverheal {

    public static final String ID = PrayerofVigor.class.getSimpleName();
    public PrayerofVigor(){
        super(ID,1,CardType.SKILL,CardRarity.COMMON,CardTarget.SELF);
        energyCosts.put(TypeEnergyHelper.Mana.Charge,3);
        energyCosts.put(TypeEnergyHelper.Mana.BaseCharge,3);
        CardModifierManager.addModifier(this,new SCVTemporalCardMod());
        setMagic(3,-1);
        setCustomVar("H",10,4);
        tags.add(EnchantmentsManager.Verse);
        setVarCalculation("H", (m, base) -> {
            AbstractPower p = null;
            if (AbstractDungeon.player != null){
                p = AbstractDungeon.player.getPower(InspirationPower.POWER_ID);
            }
            if(p != null){
                return base + p.amount;
            } else return base;
        });
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
        if (energyCosts.get(TypeEnergyHelper.Mana.Charge) <= 1){
            Wiz.atb(new CureAction(customVar( "H")));
            Wiz.atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    onOverheal(StigmataManager.Overheal);
                }
            });
        }
    }

    @Override
    public void upgrade() {
        energyCosts.put(TypeEnergyHelper.Mana.Charge,2);
        energyCosts.put(TypeEnergyHelper.Mana.BaseCharge,2);
        super.upgrade();
    }

    @Override
    public void onOverheal(int amount) {
        if (amount > 0){
            Wiz.applyToSelfTemp(new StrengthPower(Wiz.Player(),amount));
        }
    }
}
