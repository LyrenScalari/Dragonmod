package dragonmod.cards.Rimedancer.Uncommon;

import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import dragonmod.CardMods.SCVTemporalCardMod;
import dragonmod.DragonMod;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.interfaces.onAttackedEnchantment;
import dragonmod.patches.EnchantmentsManager;
import dragonmod.powers.Rimedancer.MarkPower;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public class ExposeWeakpoint extends AbstractRimedancerCard implements onAttackedEnchantment {
    public static final String ID = ExposeWeakpoint.class.getSimpleName();
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
    public ExposeWeakpoint(){
        super(ID,2,CardType.SKILL,CardRarity.UNCOMMON,CardTarget.ENEMY);
        energyCosts.put(TypeEnergyHelper.Mana.Charge,-1);
        energyCosts.put(TypeEnergyHelper.Mana.BaseCharge,-1);
        CardModifierManager.addModifier(this,new SCVTemporalCardMod());
        tags.add(DragonMod.Enchantment);
        setMagic(1,1);
        setMagic2(1);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                EnchantmentsManager.addCard(ExposeWeakpoint.this,true,m);
            }
        });
    }

    @Override
    public int EnchantedOnAttacked(AbstractCreature owner, int dmgamt, DamageInfo info) {
        if (owner != info.owner){
            if (Math.max(dmgamt-owner.currentBlock,0) > 0){
                Wiz.applyToEnemy((AbstractMonster) owner,new VulnerablePower(owner,magicNumber,false));
                Wiz.applyToEnemy((AbstractMonster) owner,new MarkPower(owner, AbstractDungeon.player,SecondMagicNumber));
            }
        }
        return dmgamt;
    }
}