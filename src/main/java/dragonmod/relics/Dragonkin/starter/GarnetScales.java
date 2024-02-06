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
import dragonmod.interfaces.OnCure;
import dragonmod.relics.BaseRelic;
import dragonmod.util.Wiz;

import static dragonmod.DragonMod.makeID;

public class GarnetScales extends BaseRelic implements OnCure {
    public static final String ID = makeID(GarnetScales.class.getSimpleName());
    public static final String NAME = "GarnetScales";
    private static RelicStrings relicStrings = CardCrawlGame.languagePack.getRelicStrings(ID);
    private static UIStrings cure = CardCrawlGame.languagePack.getUIString("dragonmod:Cure");
    public GarnetScales() {
        super(ID, NAME, RelicTier.STARTER, LandingSound.CLINK);
        description = relicStrings.DESCRIPTIONS[0];
        counter = 3;
        tips.clear();
        tips.add(new PowerTip(name, description));
        tips.add(new PowerTip(cure.TEXT[0],cure.TEXT[1]));
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    @Override
    public void OnCureBlock(int block) {
        if (AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && block > 0) {
            Wiz.atb(new RelicAboveCreatureAction(AbstractDungeon.player,this));
            AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
            Wiz.atb(new SmiteAction(target,new DamageInfo(AbstractDungeon.player,counter, DamageInfo.DamageType.THORNS)));
        }
    }
    @Override
    public void OnCureHeal(int heal) {
        if (AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && heal > 0) {
            Wiz.atb(new RelicAboveCreatureAction(AbstractDungeon.player,this));
            AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
            Wiz.atb(new SmiteAction(target,new DamageInfo(AbstractDungeon.player,counter, DamageInfo.DamageType.THORNS)));
        }
    }
}
