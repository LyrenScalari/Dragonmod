package dragonmod.powers.Rimedancer;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DragonMod;
import dragonmod.orbs.Icicle;
import dragonmod.powers.BasePower;
import dragonmod.interfaces.OnChannelOrbPower;
import dragonmod.util.Wiz;

import static dragonmod.DragonMod.powerPath;
import static dragonmod.ui.TextureLoader.getTextureNull;

public class Subzero extends BasePower implements CloneablePowerInterface, OnChannelOrbPower {
    public AbstractCreature source;
    public static final String POWER_ID = DragonMod.makeID("Subzero");
    public Subzero(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(POWER_ID,PowerType.BUFF,false,owner,source, amount);
        updateDescription();
        region128 = new TextureAtlas.AtlasRegion(getTextureNull(powerPath("large/Subzero.png")), 0, 0, getTextureNull(powerPath("large/Subzero.png")).getWidth(),
                getTextureNull(powerPath("large/Subzero.png")).getHeight());
    }

    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, Color.NAVY.cpy());
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount/2 + DESCRIPTIONS[1];
    }
    @Override
    public AbstractPower makeCopy() {
        return new Subzero(owner, source, amount);
    }

    @Override
    public void onChannelOrb(AbstractOrb Orb) {
        if (Orb instanceof Icicle){
            ((Icicle) Orb).setBasePassiveAmount(amount/2);
            ((Icicle) Orb).setBaseEvokeAmount(amount/2);
            Wiz.atb(new ReducePowerAction(owner,owner,this,amount/2));
        }
    }
    public float[] _lightsOutGetXYRI() {
        return new float[] {owner.hb.cX, owner.hb.cY, (float) (owner.hb.width+(0.01*(amount/2))), 0.05f *(amount/2)};
    }

    public Color[] _lightsOutGetColor() {
        return new Color[] {Color.NAVY.cpy()};
    }
}
