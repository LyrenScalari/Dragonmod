package dragonmod.powers.Dragonkin;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DragonMod;
import dragonmod.powers.BasePower;
import dragonmod.util.TextureLoader;

public class PerseverancePower extends BasePower implements CloneablePowerInterface, OnReceivePowerPower {
    public AbstractCreature source;
    public static final String POWER_ID = DragonMod.makeID("Perseverance");
    private static final Texture tex84 = TextureLoader.getTexture(DragonMod.powerPath("Fury.png"));
    private static final Texture tex32 = TextureLoader.getTexture(DragonMod.powerPath("Fury.png"));
    public PerseverancePower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
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
    public void atStartOfTurn(){
        addToBot(new RemoveSpecificPowerAction(owner,owner,this));
    }
    @Override
    public AbstractPower makeCopy() {
        return new PerseverancePower(owner, source, amount);
    }

    @Override
    public boolean onReceivePower(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1) {
        addToBot(new DamageAction(owner,new DamageInfo(owner,amount, DamageInfo.DamageType.THORNS)));
        return false;
    }
}