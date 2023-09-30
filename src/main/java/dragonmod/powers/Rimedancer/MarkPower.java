package dragonmod.powers.Rimedancer;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DragonMod;
import dragonmod.powers.BasePower;

import static dragonmod.DragonMod.powerPath;
import static dragonmod.ui.TextureLoader.getTextureNull;

public class MarkPower extends BasePower implements CloneablePowerInterface {
    public AbstractCreature source;
    public static final String POWER_ID = DragonMod.makeID("Mark");
    public MarkPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(POWER_ID,PowerType.DEBUFF,false,owner,source, amount);
        updateDescription();
        region128 = new TextureAtlas.AtlasRegion(getTextureNull(powerPath("large/Mark.png")), 0, 0, getTextureNull(powerPath("large/Mark.png")).getWidth(),
                getTextureNull(powerPath("large/Mark.png")).getHeight());
    }
    public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
        return damage + amount;
    }
    @Override
    public void atEndOfTurn(boolean isPlayer) {
        flash();
        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    @Override
    public AbstractPower makeCopy() {
        return new MarkPower(owner, source, amount);
    }
    @SpirePatch2(clz = AbstractOrb.class, method = "applyLockOn")
    public static class ApplyLockOnPatch {
        @SpirePostfixPatch
        public static int ApplyLockOnPatch(int __result, AbstractCreature target, int dmg) {
            if (target.hasPower(MarkPower.POWER_ID)){
                return __result + target.getPower(MarkPower.POWER_ID).amount;
            } else {
                return __result;
            }
        }
    }
}
