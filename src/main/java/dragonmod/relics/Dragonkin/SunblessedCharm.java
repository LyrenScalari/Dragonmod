package dragonmod.relics.Dragonkin;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import dragonmod.powers.Dragonkin.DivineConvictionpower;
import dragonmod.relics.BaseRelic;
import dragonmod.interfaces.onExaltPower;

import static dragonmod.DragonMod.makeID;

public class SunblessedCharm extends BaseRelic implements onExaltPower {
    public static final String ID = makeID(SunblessedCharm.class.getSimpleName());
    public static final String NAME = "Sunblessedcharm";
    public SunblessedCharm() {
        super(ID, NAME, RelicTier.UNCOMMON, LandingSound.HEAVY);
        tips.clear();
        tips.add(new PowerTip(name, description));
        counter = 3;
    }

    public void atBattleStart() {
        this.flash();
        this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DivineConvictionpower(AbstractDungeon.player,
                AbstractDungeon.player,counter)));
        this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }
    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void triggerOnExalt() {
        addToBot(new DrawCardAction(1));
    }
}