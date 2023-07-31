package dragonmod.orbs;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import dragonmod.DragonMod;
import dragonmod.powers.Dragonkin.BorrowedTimePower;
import dragonmod.powers.Dragonkin.DivineConvictionpower;
import dragonmod.util.AbstractSeal;
import dragonmod.util.Wiz;


public class LightspeedSeal extends AbstractSeal {
    public static final String ORB_ID = DragonMod.makeID("Lightspeed");
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    public AbstractCard Source;
    public LightspeedSeal(int Pow, int Pain, AbstractCard source){
        super();
        name = orbString.NAME;
        PainAmount = Pain;
        BreakAmount = Pow;
        Source = source;
    }
    public void Break(){
        super.Break();
        if (AbstractDungeon.player.hasPower(DivineConvictionpower.POWER_ID)){
            if (AbstractDungeon.player.getPower(DivineConvictionpower.POWER_ID).amount >= BreakAmount){
                AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(AbstractDungeon.player,AbstractDungeon.player,
                        AbstractDungeon.player.getPower(DivineConvictionpower.POWER_ID),BreakAmount));
                AbstractDungeon.actionManager.addToBottom(new DrawCardAction(BreakAmount));
            }
            Wiz.applyToSelf(new BorrowedTimePower(AbstractDungeon.player,1));
        }
    }
    public void updateDescription() {
        description = DESCRIPTIONS[0] + PainAmount + DESCRIPTIONS[1] + DESCRIPTIONS[2] + BreakAmount + DESCRIPTIONS[3];
    }
}