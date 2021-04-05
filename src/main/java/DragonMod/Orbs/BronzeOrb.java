package DragonMod.Orbs;

import DragonMod.DragonMod;
import basemod.abstracts.CustomOrb;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

public class BronzeOrb extends CustomOrb {
    public static final String ORB_ID = DragonMod.makeID("Bronze");
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    public BronzeOrb(String ID, String NAME, int basePassiveAmount, int baseEvokeAmount, String passiveDescription, String evokeDescription, String imgPath) {
        super(ID, NAME, basePassiveAmount, baseEvokeAmount, passiveDescription, evokeDescription, imgPath);
    }

    @Override
    public void onEvoke() {

    }
    @Override
    public void applyFocus() {
    }
    @Override
    public void updateDescription() {
        this.applyFocus();
        this.description = orbString.DESCRIPTION[0] + this.passiveAmount + orbString.DESCRIPTION[1] + this.evokeAmount + orbString.DESCRIPTION[2];
    }
    @Override
    public AbstractOrb makeCopy() {
        return null;
    }

    @Override
    public void playChannelSFX() {

    }
}
