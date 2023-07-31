package dragonmod.orbs;


import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import dragonmod.DragonMod;
import dragonmod.util.AbstractSeal;

public class FortitudeSeal extends AbstractSeal {
    public static final String ORB_ID = DragonMod.makeID("Fortitude");
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    public int counter = 0;
    public AbstractCard Source;
    public FortitudeSeal(int Pow, int Pain, AbstractCard source){
        super();
        name = orbString.NAME;
        basePainAmount = PainAmount = Pain;
        BreakAmount = Pow;
        Source = source;
    }
    public void onStartOfTurn() {
        if (PainAmount <= 0){
            PainAmount = basePainAmount;
        }
        DontBreak = true;
        reset = true;
    }
    public void Break(){
        super.Break();
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player,BreakAmount));
        counter += 1;
    }
    public void updateDescription() {
        description = DESCRIPTIONS[0] + PainAmount + DESCRIPTIONS[1] + DESCRIPTIONS[2] + BreakAmount + DESCRIPTIONS[3];
    }
}