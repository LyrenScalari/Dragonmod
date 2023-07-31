package dragonmod.orbs;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.powers.Dragonkin.Scorchpower;
import dragonmod.util.AbstractSeal;


public class SparkSeal extends AbstractSeal

    {
    public static final String ORB_ID = DragonMod.makeID("Spark");
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    public SparkSeal(int Pow, int Pain){
        super();
        name = orbString.NAME;
        basePainAmount = PainAmount = Pain;
        BreakAmount = Pow;
    }
    public void Break(){
        super.Break();
        AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
        if (target != null){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target,AbstractDungeon.player,new Scorchpower(target,AbstractDungeon.player,BreakAmount)));
        }
    }
    public void updateDescription() {
        description = DESCRIPTIONS[0] + PainAmount + DESCRIPTIONS[1] + DESCRIPTIONS[2] + BreakAmount + DESCRIPTIONS[3];
    }
}

