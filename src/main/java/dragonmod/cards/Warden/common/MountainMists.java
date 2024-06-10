package dragonmod.cards.Warden.common;

import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import dragonmod.CardMods.SCVTemporalCardMod;
import dragonmod.cards.Warden.AbstractWardenCard;
import dragonmod.interfaces.onAttackedEnchantment;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public class MountainMists extends AbstractWardenCard implements onAttackedEnchantment {
    public static final String ID = MountainMists.class.getSimpleName();
    private static final UIStrings EnchantmentTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:Enchantment");
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
    public MountainMists(){
        super(ID,1,CardType.SKILL,CardRarity.COMMON,CardTarget.SELF);
        setBlock(8,4);
        setMagic(4,2);
        energyCosts.put(TypeEnergyHelper.Mana.Charge,2);
        energyCosts.put(TypeEnergyHelper.Mana.BaseCharge,2);
        CardModifierManager.addModifier(this,new SCVTemporalCardMod());
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.block(p,block);
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                EnchantmentsManager.addCard(MountainMists.this,true,p);
            }
        });
    }

    @Override
    public int EnchantedOnAttacked(AbstractCreature owner, int dmgamt, DamageInfo info) {
        if (owner != info.owner) {
            Wiz.applyToEnemy((AbstractMonster) info.owner, new PoisonPower(info.owner,Wiz.Player(),magicNumber));
        }
        return dmgamt;
    }
}
