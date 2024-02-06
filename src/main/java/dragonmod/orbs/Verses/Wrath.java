package dragonmod.orbs.Verses;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.actions.SmiteAction;
import dragonmod.util.AbstractSeal;
import dragonmod.util.Wiz;

public class Wrath extends AbstractSeal {
    public static final String ORB_ID = DragonMod.makeID("Wrath");
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    public Wrath(int Pow, int Pain) {
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
        AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
        Wiz.atb(new SmiteAction(target,new DamageInfo(Wiz.Player(),BreakAmount, DamageInfo.DamageType.THORNS)));
    }
    public void updateDescription() {
        description = DESCRIPTIONS[0] + PainAmount + DESCRIPTIONS[1] + DESCRIPTIONS[3] + BreakAmount + DESCRIPTIONS[4] + DESCRIPTIONS[2];
    }
}
