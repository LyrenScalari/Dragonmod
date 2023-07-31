package dragonmod.orbs;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import dragonmod.DragonMod;
import dragonmod.util.AbstractSeal;

public class WisdomSeal extends AbstractSeal {
    public static final String ORB_ID = DragonMod.makeID("Wisdom");
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    public WisdomSeal(int Pow, int Pain){
        super();
        Sealstrings = orbString;
        PainAmount = Pain;
        name = orbString.NAME;
        basePainAmount = BreakAmount = Pow;
    }
    public void Break(){
        super.Break();
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(BreakAmount));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new DrawCardNextTurnPower(AbstractDungeon.player,BreakAmount)));
    }
    public void updateDescription() {
        if (BreakAmount < 2){
            description = DESCRIPTIONS[0] + PainAmount + DESCRIPTIONS[1] + DESCRIPTIONS[2] + DESCRIPTIONS[3] + BreakAmount + DESCRIPTIONS[4] + DESCRIPTIONS[7] + DESCRIPTIONS[6] + DESCRIPTIONS[3]+ BreakAmount+ DESCRIPTIONS[4]+ DESCRIPTIONS[8];
        } else description = DESCRIPTIONS[0] + PainAmount + DESCRIPTIONS[1] + DESCRIPTIONS[2] + DESCRIPTIONS[3] + BreakAmount + DESCRIPTIONS[4]+ DESCRIPTIONS[5] + DESCRIPTIONS[7] + DESCRIPTIONS[6] + DESCRIPTIONS[3]+ BreakAmount+ DESCRIPTIONS[4]+ DESCRIPTIONS[5]+ DESCRIPTIONS[8];
    }
}
