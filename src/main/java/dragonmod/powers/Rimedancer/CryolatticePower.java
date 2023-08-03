package dragonmod.powers.Rimedancer;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DragonMod;
import dragonmod.actions.GainCrystalOrbSlotAction;
import dragonmod.orbs.Icicle;
import dragonmod.powers.BasePower;
import dragonmod.util.Wiz;

public class CryolatticePower extends BasePower implements CloneablePowerInterface {
    public AbstractCreature source;
    public boolean used = true;

    public static final String POWER_ID = DragonMod.makeID("CryolatticePower");
    public CryolatticePower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(POWER_ID,PowerType.BUFF,false,owner,source, amount);
        priority = 70;
        this.loadRegion("");
        updateDescription();
    }
    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, Color.CYAN.cpy());
    }
    public void onEvokeOrb(AbstractOrb orb) {
        if (orb instanceof Icicle && !used){
            flash();
            Wiz.atb(new GainCrystalOrbSlotAction(amount));
            used = false;
        }
    }
    public void atStartOfTurnPostDraw() {
        used = true;
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    @Override
    public AbstractPower makeCopy() {
        return new Chillpower(owner, source, amount);
    }
}