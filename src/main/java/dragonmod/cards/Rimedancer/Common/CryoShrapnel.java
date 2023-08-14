package dragonmod.cards.Rimedancer.Common;

import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import dragonmod.CardMods.SCVShatterMod;
import dragonmod.actions.ShatterAction;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.cards.Rimedancer.Special.FrozenShiv;
import dragonmod.orbs.Icicle;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public class CryoShrapnel extends AbstractRimedancerCard {

    public static final String ID = CryoShrapnel.class.getSimpleName();
    private static final UIStrings holyTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:ShatterTooltip");
    public CryoShrapnel() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        setDamage(7, 3);
        setCostUpgrade(0);
        cardToPreview.add(new FrozenShiv());
        cardToPreview.add(new Shiv());
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
    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new ShatterAction(energyCosts,()-> new AbstractGameAction() {
            @Override
            public void update() {
                Wiz.att(new MakeTempCardInHandAction(new Shiv()));
                Wiz.att(new MakeTempCardInHandAction(new FrozenShiv()));
                isDone = true;
            }
        }));
    }
}
