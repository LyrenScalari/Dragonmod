package dragonmod.relics.Dragonkin.starter;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import dragonmod.actions.SmiteAction;
import dragonmod.powers.Dragonkin.InspirationPower;
import dragonmod.relics.BaseRelic;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.Wiz;

import static dragonmod.DragonMod.makeID;

public class GarnetScales extends BaseRelic {
    public static final String ID = makeID(GarnetScales.class.getSimpleName());
    public static final String NAME = "GarnetScales";
    private static RelicStrings relicStrings = CardCrawlGame.languagePack.getRelicStrings(ID);
    private static UIStrings verse = CardCrawlGame.languagePack.getUIString("dragonmod:Verse");
    private static UIStrings Inspiration = CardCrawlGame.languagePack.getUIString("dragonmod:Inspiration");
    public GarnetScales() {
        super(ID, NAME, RelicTier.STARTER, LandingSound.CLINK);
        description = relicStrings.DESCRIPTIONS[0];
        counter = 5;
        tips.clear();
        tips.add(new PowerTip(name, description));
        tips.add(new PowerTip(verse.TEXT[0],verse.TEXT[1]));
        tips.add(new PowerTip(Inspiration.TEXT[0], Inspiration.TEXT[1]));
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atTurnStartPostDraw() {
        if (EnchantmentsManager.VerseCount() > 0){
            Wiz.atb(new RelicAboveCreatureAction(AbstractDungeon.player,this));
            Wiz.applyToSelf(new InspirationPower(Wiz.Player(),Wiz.Player(),1));
            AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
            Wiz.atb(new SmiteAction(target,new DamageInfo(AbstractDungeon.player,counter, DamageInfo.DamageType.THORNS)));
        }
    }

    @Override
    public int onPlayerHeal(int healAmount) {
        if (AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && healAmount > 0) {
            Wiz.atb(new RelicAboveCreatureAction(AbstractDungeon.player,this));
            Wiz.applyToSelf(new InspirationPower(Wiz.Player(),Wiz.Player(),1));
            AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
            Wiz.atb(new SmiteAction(target,new DamageInfo(AbstractDungeon.player,counter, DamageInfo.DamageType.THORNS)));
        }
        return super.onPlayerHeal(healAmount);
    }
}
