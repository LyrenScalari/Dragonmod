package dragonmod.relics.Dragonkin;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.powers.Dragonkin.SanctifyPower;
import dragonmod.relics.BaseRelic;
import dragonmod.util.Wiz;

import static dragonmod.DragonMod.makeID;

public class GarnetScales extends BaseRelic {
    public static final String ID = makeID(GarnetScales.class.getSimpleName());
    public static final String NAME = "GarnetScales";
    private static RelicStrings relicStrings = CardCrawlGame.languagePack.getRelicStrings(ID);
    private static UIStrings sanc = CardCrawlGame.languagePack.getUIString("dragonmod:Sanctify");
    private static UIStrings cure = CardCrawlGame.languagePack.getUIString("dragonmod:Cure");
    public GarnetScales() {
        super(ID, NAME, RelicTier.STARTER, LandingSound.CLINK);
        description = relicStrings.DESCRIPTIONS[0];
        counter = 5;
        tips.clear();
        tips.add(new PowerTip(name, description));
        tips.add(new PowerTip(sanc.TEXT[0],sanc.TEXT[1]));
        tips.add(new PowerTip(cure.TEXT[0],cure.TEXT[1]));
    }

    @Override
    public void atPreBattle() {
        AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
        Wiz.applyToEnemy(target,new SanctifyPower(target,AbstractDungeon.player,counter));
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
