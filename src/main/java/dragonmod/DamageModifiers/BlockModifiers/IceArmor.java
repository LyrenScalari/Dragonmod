package dragonmod.DamageModifiers.BlockModifiers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.blockmods.AbstractBlockModifier;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import dragonmod.DragonMod;
import dragonmod.powers.Rimedancer.Chillpower;
import dragonmod.util.Wiz;

public class IceArmor extends AbstractBlockModifier {
    public int lastblock;
    boolean inherent;
    private static final UIStrings holyTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:IceArmor");
    public IceArmor(boolean inherent) {
        this.inherent = inherent;
    }

    public AbstractBlockModifier makeCopy() {
        return new IceArmor(inherent);
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

    public void onThisBlockDamaged(DamageInfo info, int lostAmount) {
        if (info.owner != owner) {
            Wiz.atb(new ApplyPowerAction(info.owner,owner,new Chillpower(info.owner,owner,lostAmount)));
        }
    }
    public Priority priority() {
        return Priority.NORMAL;
    }

    public Texture customBlockImage() {
        return DragonMod.ICE_ARMOR_ICON;
    }

    public Color blockTextColor() {
        return CardHelper.getColor(150.0f, 0.0f, 15.0f);
    }
}
