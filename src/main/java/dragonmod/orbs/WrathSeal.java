package dragonmod.orbs;

import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.actions.SmiteAction;
import dragonmod.util.AbstractSeal;


public class WrathSeal extends AbstractSeal {
    public static final String ORB_ID = DragonMod.makeID("Wrath");
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    public WrathSeal(int Pow, int Pain){
        super();
        name = orbString.NAME;
        basePainAmount = PainAmount = Pain;
        BreakAmount = Pow;
    }
    public void Break(){
        super.Break();
        AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
        if (target != null){
            AbstractDungeon.actionManager.addToBottom(new SFXAction("ORB_LIGHTNING_EVOKE"));
            AbstractDungeon.actionManager.addToBottom(new SmiteAction(target, new DamageInfo(AbstractDungeon.player, BreakAmount, DamageInfo.DamageType.THORNS)));
        }
    }
    public void updateDescription() {
        description = DESCRIPTIONS[0] + PainAmount + DESCRIPTIONS[1] + DESCRIPTIONS[2] + BreakAmount + DESCRIPTIONS[3];
    }
}
