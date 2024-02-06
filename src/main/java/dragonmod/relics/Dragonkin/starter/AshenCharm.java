package dragonmod.relics.Dragonkin.starter;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import dragonmod.interfaces.OnCure;
import dragonmod.powers.Dragonkin.SacrificePower;
import dragonmod.relics.BaseRelic;
import dragonmod.util.Wiz;

import static dragonmod.DragonMod.makeID;

public class AshenCharm extends BaseRelic implements OnCure {
    public static final String ID = makeID(AshenCharm.class.getSimpleName());
    public static final String NAME = "AshenCharm";
    private static RelicStrings relicStrings = CardCrawlGame.languagePack.getRelicStrings(ID);
    private static UIStrings sacrifice = CardCrawlGame.languagePack.getUIString("dragonmod:Sacrifice");
    public AshenCharm() {
        super(ID, NAME, RelicTier.STARTER, LandingSound.CLINK);
        description = relicStrings.DESCRIPTIONS[0];
        tips.clear();
        tips.add(new PowerTip(name, description));
        tips.add(new PowerTip(sacrifice.TEXT[0],sacrifice.TEXT[1]));
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void OnCureBlock(int block) {

    }

    @Override
    public void OnCureHeal(int heal) {
        if (AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && heal > 0) {
            Wiz.atb(new RelicAboveCreatureAction(AbstractDungeon.player,this));
            Wiz.applyToSelf(new SacrificePower(AbstractDungeon.player, AbstractDungeon.player, heal));
        }
    }
}
