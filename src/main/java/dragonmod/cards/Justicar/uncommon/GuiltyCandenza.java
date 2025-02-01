package dragonmod.cards.Justicar.uncommon;

import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.SCVExaltCardmod;
import dragonmod.CardMods.SCVTemporalCardMod;
import dragonmod.actions.ExaltAction;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.interfaces.TurnStartEnchantment;
import dragonmod.powers.Dragonkin.ConfessionPower;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public class GuiltyCandenza extends AbstractJusticarCard implements TurnStartEnchantment {

    public static final String ID = GuiltyCandenza.class.getSimpleName();
    public GuiltyCandenza(){
        super(ID,1, AbstractCard.CardType.SKILL, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        setMagic(6,4);
        energyCosts.put(TypeEnergyHelper.Mana.Exalt,3);
        energyCosts.put(TypeEnergyHelper.Mana.Charge,2);
        energyCosts.put(TypeEnergyHelper.Mana.BaseCharge,2);
        tags.add(EnchantmentsManager.Verse);
        CardModifierManager.addModifier(this,new SCVExaltCardmod());
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
        retVal.add(new TooltipInfo(inspirationString.TEXT[0],inspirationString.TEXT[1]));
        return retVal;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new ExaltAction(energyCosts,()->new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                Wiz.applyToSelf(new ConfessionPower(p,magicNumber));
            }
        }));
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                EnchantmentsManager.addCard(GuiltyCandenza.this,true,p);
            }
        });
    }

    @Override
    public void EnchantedTurnStart(AbstractCreature owner) {
        Wiz.applyToSelf(new ConfessionPower(Wiz.Player(),magicNumber));
        Wiz.dmg(owner,new DamageInfo(owner,EnchantmentsManager.getDevotion(), DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        Wiz.atb(new DamageAllEnemiesAction(Wiz.Player(),EnchantmentsManager.getDevotion(), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }
}
