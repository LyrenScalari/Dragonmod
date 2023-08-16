package dragonmod.cards.Rimedancer.Uncommon;

import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import dragonmod.CardMods.SCVShatterMod;
import dragonmod.actions.IcicleFanAction;
import dragonmod.actions.ShatterAction;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.orbs.Icicle;
import dragonmod.powers.Rimedancer.BleedPower;
import dragonmod.powers.Rimedancer.Subzero;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public class RupturingShot extends AbstractRimedancerCard {
    public static final String ID = RupturingShot.class.getSimpleName();
    private static final UIStrings holyTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:ShatterTooltip");
    public RupturingShot() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        setDamage(8, 4);
        setMagic(8,4);
        setMagic2(4,2);
        energyCosts.put(TypeEnergyHelper.Mana.Shatter,2);
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
        ArrayList<AbstractOrb> orbs = new ArrayList<>();
        for (AbstractOrb o : Wiz.adp().orbs) {
            if (o instanceof Icicle && orbs.size() < energyCosts.get(TypeEnergyHelper.Mana.Shatter)) {
                orbs.add(o);
            }
        }
        Wiz.atb(new IcicleFanAction(orbs, m.hb, Color.CYAN));
        for (int i = 0; i < energyCosts.get(TypeEnergyHelper.Mana.Shatter); i++){
            Wiz.atb(new ShatterAction());
        }
        Wiz.dmg(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL));
        Wiz.applyToEnemy(m,new BleedPower(m,magicNumber));
        Wiz.applyToSelf(new Subzero(p,p,SecondMagicNumber));
    }
}