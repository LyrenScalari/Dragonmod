package dragonmod.powers.Dragonkin;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import dragonmod.DragonMod;
import dragonmod.powers.BasePower;
import dragonmod.util.TextureLoader;
import dragonmod.util.Wiz;
import dragonmod.util.onExaltPower;

import static dragonmod.DragonMod.powerPath;

public class CrusaderFormpower extends BasePower implements CloneablePowerInterface, onExaltPower {
    public AbstractCreature source;
    public static final String POWER_ID = DragonMod.makeID("CrusaderFormpower");
    private static final Texture tex84 = TextureLoader.getTexture(powerPath("CrusaderForm.png"));
    private static final Texture tex32 = TextureLoader.getTexture(powerPath("CrusaderForm.png"));

    public CrusaderFormpower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(POWER_ID,PowerType.BUFF,false,owner,source, amount);
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        updateDescription();
    }


    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new CrusaderFormpower(owner, source, amount);
    }

    @Override
    public void triggerOnExalt() {
        Wiz.applyToSelfNextTurn(new DivineConvictionpower(owner,owner,amount));
        Wiz.applyToSelf(new DrawCardNextTurnPower(owner,1));
    }
}
