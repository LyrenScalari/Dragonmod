package dragonmod.cards.Justicar.common;

import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import dragonmod.CardMods.SCVTemporalCardMod;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.interfaces.TurnStartEnchantment;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public class PrayerofWrath extends AbstractJusticarCard implements TurnStartEnchantment {

    public static final String ID = PrayerofWrath.class.getSimpleName();
    public PrayerofWrath(){
        super(ID,2,CardType.ATTACK,CardRarity.COMMON,CardTarget.SELF);
        setMagic(3,1);
        setDamage(15,3);
        energyCosts.put(TypeEnergyHelper.Mana.Charge,3);
        energyCosts.put(TypeEnergyHelper.Mana.BaseCharge,3);
        tags.add(EnchantmentsManager.Verse);
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
        return retVal;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                EnchantmentsManager.addCard(PrayerofWrath.this,true,p);
            }
        });
    }

    @Override
    public void EnchantedTurnStart(AbstractCreature owner) {
        if (energyCosts.get(TypeEnergyHelper.Mana.Charge) <= 1){
            Wiz.atb(new DamageRandomEnemyAction(new DamageInfo(Wiz.Player(),baseDamage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.LIGHTNING));
        }
        Wiz.applyToSelf(new VigorPower(Wiz.Player(),magicNumber));
    }
}
