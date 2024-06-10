package dragonmod.powers.Rimedancer.powercards;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.EchoPower;
import dragonmod.DragonMod;
import dragonmod.actions.GainCrystalOrbSlotAction;
import dragonmod.orbs.Icicle;
import dragonmod.powers.BasePower;
import dragonmod.powers.Rimedancer.onRemoveOrbPower;
import dragonmod.util.Wiz;

public class CryolatticePower extends BasePower implements CloneablePowerInterface, onRemoveOrbPower {
    public AbstractCreature source;
    public boolean used = false;
    private static AbstractPower powerToLose = new EchoPower(null,0);
    public static final String POWER_ID = DragonMod.makeID("Cryolattice");
    public CryolatticePower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(POWER_ID, NeutralPowertypePatch.NEUTRAL,false,owner,source, amount);
        priority = 70;
        this.img = powerToLose.img;
        this.region48 = powerToLose.region48;
        this.region128 = powerToLose.region128;
        this.loadRegion("echo");
        updateDescription();
    }
    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, Color.CYAN.cpy());
    }
    public void atStartOfTurnPostDraw() {
        used = false;
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    @Override
    public AbstractPower makeCopy() {
        return new CryolatticePower(owner, source, amount);
    }

    @Override
    public void onRemoveOrb(AbstractOrb orb) {
        if (orb instanceof Icicle && !used){
            flash();
            Wiz.atb(new GainCrystalOrbSlotAction(amount));
            used = true;
        }
    }
}