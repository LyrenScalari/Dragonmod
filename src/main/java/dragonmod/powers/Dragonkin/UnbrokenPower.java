package dragonmod.powers.Dragonkin;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import dragonmod.DragonMod;
import dragonmod.powers.BasePower;
import dragonmod.util.ReciveDamageEffect;
import dragonmod.util.TextureLoader;
import dragonmod.util.Wiz;

public class UnbrokenPower extends BasePower implements CloneablePowerInterface, ReciveDamageEffect {
    public AbstractCreature source;
    public static final String POWER_ID = DragonMod.makeID("Unbroken");
    public boolean used = false;
    private static final Texture tex84 = TextureLoader.getTexture(DragonMod.powerPath("AcidArmor.png"));
    private static final Texture tex32 = TextureLoader.getTexture(DragonMod.powerPath("AcidArmor.png"));

    public UnbrokenPower(final AbstractCreature owner) {
        super(POWER_ID,PowerType.BUFF,false,owner,owner, -1);
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
    @Override
    public AbstractPower makeCopy() {
        return new UnbrokenPower(owner);
    }
    public void atStartOfTurnPostDraw() {
        used = false;
    }
    @Override
    public void onReciveDamage(int damage) {
        if (!used) {
            Wiz.applyToSelf(new NextTurnBlockPower(owner, damage, name));
            used = true;
        }
    }
}
