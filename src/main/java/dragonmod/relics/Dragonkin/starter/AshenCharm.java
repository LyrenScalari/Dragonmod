package dragonmod.relics.Dragonkin.starter;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import dragonmod.relics.BaseRelic;

import static dragonmod.DragonMod.makeID;

public class AshenCharm extends BaseRelic {
    public static final String ID = makeID(AshenCharm.class.getSimpleName());
    public static final String NAME = "AshenCharm";
    private static RelicStrings relicStrings = CardCrawlGame.languagePack.getRelicStrings(ID);
    private static UIStrings sacrifice = CardCrawlGame.languagePack.getUIString("dragonmod:Sacrifice");
    public AshenCharm() {
        super(ID,NAME, RelicTier.DEPRECATED, LandingSound.CLINK);
        description = relicStrings.DESCRIPTIONS[0];
        tips.clear();
        tips.add(new PowerTip(name, description));
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
