package dragonmod.cards.Justicar.uncommon;

import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.CardMods.SCVTemporalCardMod;
import dragonmod.actions.CureAction;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.interfaces.TurnStartEnchantment;
import dragonmod.powers.Dragonkin.ZealPower;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public class BeaconofLight extends AbstractJusticarCard implements TurnStartEnchantment {

    public static final String ID = BeaconofLight.class.getSimpleName();
    public BeaconofLight(){
        super(ID,1,CardType.SKILL,CardRarity.UNCOMMON,CardTarget.SELF);
        setMagic(2);
        tags.add(EnchantmentsManager.Verse);
        energyCosts.put(TypeEnergyHelper.Mana.Charge,2);
        energyCosts.put(TypeEnergyHelper.Mana.BaseCharge,2);
        CardModifierManager.addModifier(this,new SCVTemporalCardMod());
        setCustomVar("H",4,1);
        setVarCalculation("H", (m, base) -> {
            AbstractPower p = null;
            if (AbstractDungeon.player != null){
                p = AbstractDungeon.player.getPower(ZealPower.POWER_ID);
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
        AbstractCard copy = makeSameInstanceOf();
        copy.purgeOnUse = true;
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                EnchantmentsManager.addCard(copy,false,Wiz.Player());
                isDone = true;
            }
        });
    }

    @Override
    public void EnchantedTurnStart(AbstractCreature owner) {
        Wiz.atb(new CureAction(customVar( "H")));
    }
}
