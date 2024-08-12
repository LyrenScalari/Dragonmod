package dragonmod.cards.Justicar.uncommon;

import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import dragonmod.CardMods.SCVTemporalCardMod;
import dragonmod.DragonMod;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.interfaces.onAttackedEnchantment;
import dragonmod.powers.general.ParryPower;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public class RebuketheViolent extends AbstractJusticarCard implements onAttackedEnchantment {
    private static final UIStrings EnchantmentTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:Enchantment");
    public static final String ID = RebuketheViolent.class.getSimpleName();
    public RebuketheViolent(){
        super(ID,2,CardType.SKILL,CardRarity.UNCOMMON,CardTarget.SELF);
        setMagic(2);
        energyCosts.put(TypeEnergyHelper.Mana.Charge,1);
        energyCosts.put(TypeEnergyHelper.Mana.BaseCharge,1);
        CardModifierManager.addModifier(this,new SCVTemporalCardMod());
        tags.add(DragonMod.Enchantment);
    }
    @Override
    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo> retVal = new ArrayList<>();
        retVal.add(new TooltipInfo(EnchantmentTooltip.TEXT[0], EnchantmentTooltip.TEXT[1]));
        return retVal;
    }

    @Override
    public List<String> getCardDescriptors() {
        ArrayList<String> retval = new ArrayList<>();
        retval.add(EnchantmentTooltip.TEXT[0]);
        retval.addAll(super.getCardDescriptors());
        return retval;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                EnchantmentsManager.addCard(RebuketheViolent.this,true,p);
            }
        });
    }
    public void upgrade(){
        super.upgrade();
        if (!upgraded){
            energyCosts.put(TypeEnergyHelper.Mana.Charge,2);
            energyCosts.put(TypeEnergyHelper.Mana.BaseCharge,2);
        }
    }

    @Override
    public int EnchantedOnAttacked(AbstractCreature owner, int dmgamt, DamageInfo info) {
        if (owner != info.owner){
            Wiz.applyToEnemy((AbstractMonster) info.owner,new WeakPower(info.owner,magicNumber,true));
            Wiz.applyToSelf(new ParryPower(Wiz.Player(),Wiz.Player(),dmgamt/2));
        }
        return dmgamt;
    }
}

