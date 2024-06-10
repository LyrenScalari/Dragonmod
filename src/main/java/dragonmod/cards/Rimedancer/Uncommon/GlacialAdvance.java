package dragonmod.cards.Rimedancer.Uncommon;

import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import dragonmod.CardMods.SCVTemporalCardMod;
import dragonmod.DamageModifiers.RangedDamage;
import dragonmod.DragonMod;
import dragonmod.actions.IcicleFanAction;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.interfaces.onRemoveOrbEnchantment;
import dragonmod.orbs.Icicle;
import dragonmod.ui.TextureLoader;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public class GlacialAdvance extends AbstractRimedancerCard implements onRemoveOrbEnchantment {
    public static final String ID = GlacialAdvance.class.getSimpleName();
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
    public GlacialAdvance(){
        super(ID,1,CardType.SKILL,CardRarity.UNCOMMON,CardTarget.SELF);
        energyCosts.put(TypeEnergyHelper.Mana.Charge,2);
        energyCosts.put(TypeEnergyHelper.Mana.BaseCharge,2);
        CardModifierManager.addModifier(this,new SCVTemporalCardMod());
        tags.add(DragonMod.Enchantment);
        DamageModifierManager.addModifier(this, new RangedDamage(true));
        setMagic(6,3);
        setMagic2(1);
        setBlock(5,3);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new GainBlockAction(p,block));
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                EnchantmentsManager.addCard(GlacialAdvance.this,true,p);
            }
        });
    }


    @Override
    public boolean EnchantedOnRemoveOrb(AbstractCreature owner, AbstractOrb removedorb) {
        Icicle tothrow = null;
        if (energyCosts.get(TypeEnergyHelper.Mana.Charge) > 0){
            for (AbstractOrb o : Wiz.Player().orbs) {
                if (o instanceof Icicle) {
                    tothrow = (Icicle) o;
                }
            }
            ArrayList<Hitbox> hbs = new ArrayList<>();
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                hbs.add(mo.hb);
            }
            if (tothrow != null) {
                Wiz.atb(new IcicleFanAction(tothrow, hbs, Color.CYAN));
            } else {
                Wiz.atb(new IcicleFanAction(TextureLoader.getTexture(DragonMod.orbPath("Icicle.png")), 1.0f, hbs, Color.CYAN, false));
            }
            Wiz.atb(new DamageAllEnemiesAction(Wiz.Player(), magicNumber, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
        return false;
    }
}
