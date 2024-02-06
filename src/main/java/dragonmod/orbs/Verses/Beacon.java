package dragonmod.orbs.Verses;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.OrbStrings;
import dragonmod.DragonMod;
import dragonmod.actions.CureAction;
import dragonmod.util.AbstractSeal;
import dragonmod.util.Wiz;

public class Beacon extends AbstractSeal {
    public static final String ORB_ID = DragonMod.makeID("Beacon");
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    public Beacon(int Pow, int Pain) {
        super();
        name = orbString.NAME;
        basePainAmount = PainAmount = Pain;
        BreakAmount = Pow;
    }
    public void Coda(){
        super.Coda();
    }
    public void Chant(){
        super.Chant();
        Wiz.atb(new CureAction(BreakAmount));
    }
    public void updateDescription() {
        description = DESCRIPTIONS[0] + PainAmount + DESCRIPTIONS[1] + DESCRIPTIONS[3] + BreakAmount + DESCRIPTIONS[4] + DESCRIPTIONS[2];
    }
}
