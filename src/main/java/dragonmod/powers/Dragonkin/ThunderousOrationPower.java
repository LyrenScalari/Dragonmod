package dragonmod.powers.Dragonkin;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DragonMod;
import dragonmod.interfaces.OnChant;
import dragonmod.powers.BasePower;
import dragonmod.ui.TextureLoader;
import dragonmod.util.Wiz;

public class ThunderousOrationPower extends BasePower implements CloneablePowerInterface, OnChant {
    public AbstractCreature source;

    public static final String POWER_ID = DragonMod.makeID("ThunderousOration");
    private static final Texture tex84 = TextureLoader.getTexture(DragonMod.powerPath("Agony.png"));
    private static final Texture tex32 = TextureLoader.getTexture(DragonMod.powerPath("Agony.png"));

    public ThunderousOrationPower(final int amount) {
        super(POWER_ID,PowerType.DEBUFF,false, Wiz.Player(),Wiz.Player(), amount);
        img = tex84;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        updateDescription();
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    @Override
    public AbstractPower makeCopy() {
        return new ThunderousOrationPower(amount);
    }

    @Override
    public void triggerOnChant() {
        Wiz.atb(new DamageAllEnemiesAction(Wiz.Player(),amount, DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.LIGHTNING));
    }
}