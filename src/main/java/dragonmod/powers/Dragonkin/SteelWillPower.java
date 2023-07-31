package dragonmod.powers.Dragonkin;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DragonMod;
import dragonmod.powers.BaseTwoAmountPower;
import dragonmod.util.ReciveDamageEffect;
import dragonmod.util.TextureLoader;

import static dragonmod.DragonMod.powerPath;

public class SteelWillPower extends BaseTwoAmountPower implements CloneablePowerInterface, ReciveDamageEffect, OnReceivePowerPower {
    public AbstractCreature source;
    public static final String POWER_ID = DragonMod.makeID("SteelWill");
    private static final Texture tex84 = TextureLoader.getTexture(powerPath("AcidArmor.png"));
    private static final Texture tex32 = TextureLoader.getTexture(powerPath("AcidArmor.png"));
    public SteelWillPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(POWER_ID, PowerType.BUFF,false,owner,source, amount);
        this.amount2 = amount;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }
    @Override
    public void atEndOfTurn(final boolean isPlayer){
        amount = 0;
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount2 + DESCRIPTIONS[1] + DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[3];
    }
    @Override
    public AbstractPower makeCopy() {
        return new SteelWillPower(owner, source, amount2);
    }

    @Override
    public void onReciveDamage(int damage) {
        amount += damage;
        updateDescription();
    }

    @Override
    public boolean onReceivePower(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1) {
        System.out.println(abstractPower.name);
        if ((amount >= amount2) && !(abstractPower instanceof PerseverancePower) && abstractPower.type == PowerType.DEBUFF){
            addToBot(new ApplyPowerAction(owner,owner,new PerseverancePower(owner,owner,abstractPower.amount)));
            return false;
        } else {
            return true;
        }
    }
}