package dragonmod.cards.Rimedancer.Uncommon;

import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.SCVTemporalCardMod;
import dragonmod.DragonMod;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.interfaces.TurnStartEnchantment;
import dragonmod.interfaces.onAttackedEnchantment;
import dragonmod.powers.Rimedancer.Chillpower;
import dragonmod.powers.Rimedancer.SlowPower;
import dragonmod.powers.general.ReinforcePower;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public class IceBarrier extends AbstractRimedancerCard implements onAttackedEnchantment, TurnStartEnchantment {

    public static final String ID = IceBarrier.class.getSimpleName();
    private static final UIStrings EnchantmentTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:Enchantment");
    public IceBarrier(){
        super(ID,1,CardType.SKILL,CardRarity.UNCOMMON,CardTarget.ALL);
        setMagic(2,1);
        energyCosts.put(TypeEnergyHelper.Mana.Charge,-1);
        energyCosts.put(TypeEnergyHelper.Mana.BaseCharge,-1);
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
        Wiz.applyToSelf(new ReinforcePower(p,p,magicNumber));
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                EnchantmentsManager.addCard(IceBarrier.this,true,p);
            }
        });
    }

    @Override
    public void EnchantedTurnStart(AbstractCreature owner) {
        energyCosts.put(TypeEnergyHelper.Mana.Charge,0);
    }

    @Override
    public int EnchantedOnAttacked(AbstractCreature owner, int dmgamt, DamageInfo info) {
        Wiz.applyToEnemy((AbstractMonster) info.owner,new Chillpower(info.owner,owner,magicNumber));
        Wiz.applyToEnemy((AbstractMonster) info.owner,new SlowPower(info.owner,magicNumber));
        return dmgamt;
    }
}
