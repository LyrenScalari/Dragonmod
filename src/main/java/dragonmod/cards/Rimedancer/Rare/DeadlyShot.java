package dragonmod.cards.Rimedancer.Rare;

import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.LockOnPower;
import dragonmod.CardMods.SCVShatterMod;
import dragonmod.DamageModifiers.RangedDamage;
import dragonmod.actions.ShatterAction;
import dragonmod.actions.ThrowIcicleAction;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.orbs.Icicle;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public class DeadlyShot extends AbstractRimedancerCard {
    public static final String ID = DeadlyShot.class.getSimpleName();
    private static final UIStrings holyTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:ShatterTooltip");
    public DeadlyShot() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        setDamage(10);
        setCostUpgrade(0);
        setMagic(2,1);
        DamageModifierManager.addModifier(this,new RangedDamage(true));
        energyCosts.put(TypeEnergyHelper.Mana.Shatter,1);
        CardModifierManager.addModifier(this,new SCVShatterMod());
    }
    @Override
    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo> retVal = new ArrayList<>();
        retVal.add(new TooltipInfo(holyTooltip.TEXT[0], holyTooltip.TEXT[1]));
        return retVal;
    }
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        } else {
            int icicles = 0;
            for (AbstractOrb o : p.orbs) {
                if (o instanceof Icicle) {
                    icicles++;
                }
            }
            return icicles >= energyCosts.get(TypeEnergyHelper.Mana.Shatter);
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Icicle tothrow = null;
        for (AbstractOrb o : Wiz.Player().orbs) {
            if (o instanceof Icicle) {
                tothrow = (Icicle) o;
                Icicle finalTothrow = tothrow;
                Wiz.atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        baseDamage += finalTothrow.passiveAmount;
                        isDone = true;
                    }
                });
            }
        }
        Wiz.atb(new ShatterAction());
        Wiz.atb(new ThrowIcicleAction(tothrow,m.hb, Color.CYAN));
        Wiz.dmg(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL));
        Wiz.applyToEnemy(m,new LockOnPower(m,magicNumber));
    }
}
