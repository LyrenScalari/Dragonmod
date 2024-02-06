package dragonmod.orbs.Verses;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.OrbStrings;
import dragonmod.DragonMod;
import dragonmod.powers.Dragonkin.ConfessionPower;
import dragonmod.util.AbstractSeal;
import dragonmod.util.Wiz;

import static dragonmod.util.HymnManager.getDevotion;

public class Hallowed extends AbstractSeal {
    public static final String ORB_ID = DragonMod.makeID("Hallowed");
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;

    public Hallowed(int Pain) {
        super();
        name = orbString.NAME;
        basePainAmount = PainAmount = 3;
        baseBreakAmount = BreakAmount = Pain;
        }
    public void Coda(){
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                Wiz.applyToSelf(new ConfessionPower(Wiz.Player(),BreakAmount));
            }
        });
    }
    public void updateDescription() {
        description = DESCRIPTIONS[0] + PainAmount + DESCRIPTIONS[1] + DESCRIPTIONS[2] + getDevotion() + DESCRIPTIONS[3];
        baseBreakAmount = BreakAmount = 3;
    }
}
