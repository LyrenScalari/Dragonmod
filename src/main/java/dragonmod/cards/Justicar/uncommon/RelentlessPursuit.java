package dragonmod.cards.Justicar.uncommon;

import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.SCVTemporalCardMod;
import dragonmod.DragonMod;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.interfaces.TurnStartEnchantment;
import dragonmod.util.EnchantmentsManager;
import dragonmod.powers.Dragonkin.Pursuit;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public class RelentlessPursuit extends AbstractJusticarCard implements TurnStartEnchantment {
    private static final UIStrings EnchantmentTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:Enchantment");
    public static final String ID = RelentlessPursuit.class.getSimpleName();
    public RelentlessPursuit(){
        super(ID,2,CardType.ATTACK,CardRarity.UNCOMMON,CardTarget.ENEMY);
        setCustomVar("WOG",8,3);
        setVarCalculation("WOG", (m, base) -> {
            if (AbstractDungeon.player != null){
                int tmp = this.baseDamage;
                this.baseDamage = base;
                if (m != null)
                    super.calculateCardDamage(m);
                else
                    super.applyPowers();
                this.baseDamage = tmp;
                if (upgraded){
                    return (int) (damage + (AbstractDungeon.player.currentBlock/1.33f));
                }
                return damage + (AbstractDungeon.player.currentBlock/2);
            } else {
                return base;
            }
        });
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
        Wiz.dmg(m,new DamageInfo(p,customVar("WOG"), DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY);
        Wiz.applyToSelf(new Pursuit(p,p,m));
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                EnchantmentsManager.addCard(RelentlessPursuit.this,true,m);
            }
        });
    }

    public void EnchantedTurnStart(AbstractCreature owner) {
        Wiz.atb(new RemoveSpecificPowerAction(Wiz.Player(),Wiz.Player(),Pursuit.POWER_ID));
    }
}