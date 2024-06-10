package dragonmod.cards.Rimedancer.Rare;

import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.SCVTemporalCardMod;
import dragonmod.DragonMod;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.interfaces.onAttackedEnchantment;
import dragonmod.powers.Rimedancer.BleedPower;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public class Scintilla extends AbstractRimedancerCard implements onAttackedEnchantment {
    public static final String ID = Scintilla.class.getSimpleName();
    private static final UIStrings EnchantmentTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:Enchantment");
    public Scintilla(){
        super(ID,2,CardType.SKILL,CardRarity.RARE,CardTarget.SELF);
        setMagic(4,2);
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
                EnchantmentsManager.addCard(Scintilla.this,true,p);
            }
        });
    }

    @Override
    public int EnchantedOnAttacked(AbstractCreature owner, int dmgamt, DamageInfo info) {
        if (info.owner != Wiz.Player()){
            Wiz.atb(new DamageAction(info.owner,new DamageInfo(Wiz.Player(),(dmgamt/2)+magicNumber, DamageInfo.DamageType.THORNS)));
            Wiz.applyToEnemy((AbstractMonster) info.owner,new BleedPower(info.owner,(dmgamt/2)+magicNumber));
            return dmgamt/2;
        }
        return dmgamt;
    }
}
