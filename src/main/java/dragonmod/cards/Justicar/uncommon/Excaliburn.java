package dragonmod.cards.Justicar.uncommon;

import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.SCVTemporalCardMod;
import dragonmod.DragonMod;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.interfaces.TriggerOnUseCard;
import dragonmod.powers.Dragonkin.Scorchpower;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;

import java.util.ArrayList;
import java.util.List;
public class Excaliburn extends AbstractJusticarCard implements TriggerOnUseCard {
    private static final UIStrings EnchantmentTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:Enchantment");
    public static final String ID = Excaliburn.class.getSimpleName();
    public Excaliburn(){
        super(ID,2,CardType.SKILL,CardRarity.UNCOMMON,CardTarget.SELF);
        setMagic(2,1);
        setMagic2(6,2);
        energyCosts.put(TypeEnergyHelper.Mana.Charge,4);
        energyCosts.put(TypeEnergyHelper.Mana.BaseCharge,4);
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
                EnchantmentsManager.addCard(Excaliburn.this,true,p);
            }
        });
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == CardType.ATTACK){
            EnchantmentsManager.ActivateEnchantments(this, Wiz.Player());
            Wiz.atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    AbstractMonster m = Wiz.getRandomEnemy();
                    Wiz.dmg(m,new DamageInfo(Wiz.Player(),SecondMagicNumber, DamageInfo.DamageType.THORNS),AttackEffect.FIRE);
                    Wiz.applyToEnemy(m,new Scorchpower(m,Wiz.Player(),magicNumber));
                    Wiz.dmg(Wiz.Player(),new DamageInfo(Wiz.Player(),magicNumber, DamageInfo.DamageType.THORNS),AttackEffect.FIRE);
                }
            });
        }
    }
}

