package dragonmod.DamageModifiers.BlockModifiers;


import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.blockmods.AbstractBlockModifier;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import java.util.ArrayList;

import static dragonmod.DragonMod.DIVINE_ARMOR_ICON;

public class DivineBlock extends AbstractBlockModifier {
    boolean inherent;
    private static final UIStrings holyTooltip = CardCrawlGame.languagePack.getUIString("theDragonkin:DivineBlockTooltip");
    public DivineBlock(boolean inherent) {
        this.inherent = inherent;
    }

    public AbstractBlockModifier makeCopy() {
        return new DivineBlock(inherent);
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
    public Priority priority() {
        return Priority.TOP;
    }

    public Color blockImageColor() {
        return CardHelper.getColor(255.0f, 255.0f, 255.0f);
    }

    public Color blockTextColor() {
        return CardHelper.getColor(255.0f, 255.0f, 255.0f);
    }

    public Texture customBlockImage() {
        return DIVINE_ARMOR_ICON;
    }

    public int amountLostAtStartOfTurn() {
        return 0;
    }


}