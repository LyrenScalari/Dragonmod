package dragonmod.DamageModifiers.BlockModifiers;


import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.blockmods.AbstractBlockModifier;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;

import java.util.ArrayList;

public class FireBlock extends AbstractBlockModifier {
    public int lastblock;
    boolean inherent;
    private static final UIStrings holyTooltip = CardCrawlGame.languagePack.getUIString("theDragonkin:FlameBarrierTooltip");
    public FireBlock(boolean inherent) {
        this.inherent = inherent;
    }

    public AbstractBlockModifier makeCopy() {
        return new FireBlock(inherent);
    }

    public boolean isInherent() {
        return inherent;
    }

    @Override
    public String getName() {
        return holyTooltip.TEXT[0];
    }

    @Override
    public String getDescription() {
        return holyTooltip.TEXT[1];
    }

    public ArrayList<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> tips = new ArrayList<>();
        tips.add(new TooltipInfo(holyTooltip.TEXT[0], holyTooltip.TEXT[1]));
        return tips;
    }
    public void onThisBlockDamaged(DamageInfo info, int lostAmount) {
        if (info.owner != owner) {
            addToBot(new DamageAction(info.owner, new DamageInfo(owner,  lostAmount, DamageInfo.DamageType.HP_LOSS)));
        }
    }
    public Priority priority() {
        return Priority.NORMAL;
    }

    public Texture customBlockImage() {
        return DragonMod.FLAME_BARRIER_ICON;
    }

    public Color blockTextColor() {
        return CardHelper.getColor(150.0f, 0.0f, 15.0f);
    }

    public int amountLostAtStartOfTurn() {
        int maxscorch = 0;
/*        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters){
            if (m.hasPower(Scorchpower.POWER_ID)){
                maxscorch += m.getPower(Scorchpower.POWER_ID).amount;
            }
        }*/
        if (getCurrentAmount()-maxscorch > 0){
            return getCurrentAmount()-maxscorch;
        } else return 0;
    }
}