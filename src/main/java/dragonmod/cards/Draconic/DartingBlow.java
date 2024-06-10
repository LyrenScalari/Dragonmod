package dragonmod.cards.Draconic;

import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import dragonmod.CardMods.SCVTemporalCardMod;
import dragonmod.DragonMod;
import dragonmod.interfaces.TurnStartEnchantment;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public class DartingBlow extends AbstractDraconicCard implements TurnStartEnchantment {
    private static final UIStrings EnchantmentTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:Enchantment");
    public static final String ID = DartingBlow.class.getSimpleName();
    public DartingBlow(){
        super(ID,1,CardType.SKILL,CardRarity.UNCOMMON,CardTarget.SELF);
        setMagic(5,3);
        setInnate(true);
        energyCosts.put(TypeEnergyHelper.Mana.Charge,1);
        energyCosts.put(TypeEnergyHelper.Mana.BaseCharge,1);
        CardModifierManager.addModifier(this,new SCVTemporalCardMod());
        tags.add(DragonMod.Enchantment);
    }
    @Override
    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo> retVal = new ArrayList<>();
        retVal.add(new TooltipInfo(EnchantmentTooltip.TEXT[0], EnchantmentTooltip.TEXT[1]));
        retVal.addAll(super.getCustomTooltips());
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
        Wiz.applyToSelf(new VigorPower(p,magicNumber));
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                EnchantmentsManager.addCard(DartingBlow.this,true,p);
            }
        });
    }

    @Override
    public void EnchantedTurnStart(AbstractCreature owner) {
        Wiz.applyToSelf(new VigorPower(owner,magicNumber));
    }
}