package dragonmod.powers.Dragonkin;


import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DragonMod;
import dragonmod.powers.BaseTwoAmountPower;
import dragonmod.relics.Dragonkin.MukySludge;
import dragonmod.util.Wiz;

public class Scorchpower extends BaseTwoAmountPower implements CloneablePowerInterface, HealthBarRenderPower {
    public AbstractCreature source;

    public static final String POWER_ID = DragonMod.makeID("Scorch");

    public Scorchpower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(POWER_ID,PowerType.DEBUFF,false,owner,source, amount);
        updateDescription();
    }
    @Override
    public float atDamageFinalGive(float d, DamageInfo.DamageType type){
        if (type == DamageInfo.DamageType.NORMAL && AbstractDungeon.player.hasRelic(MukySludge.ID)) {
            int temp = amount;
            return d - temp;
        }
        return d;
    }
    @Override
    public void onAttack(DamageInfo info, int d, AbstractCreature target){
        if (info.type == DamageInfo.DamageType.NORMAL && AbstractDungeon.player.hasRelic(MukySludge.ID)) {
            this.flash();
            AbstractDungeon.actionManager.addToTop(
                    new ReducePowerAction(this.owner, this.owner, this,1));
        }
    }
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type == DamageInfo.DamageType.NORMAL){
            Wiz.atb(new DamageAction(owner,new DamageInfo(owner, (int) Math.ceil((amount*0.25)), DamageInfo.DamageType.THORNS)));
            Wiz.att(new ReducePowerAction(owner,owner,this,(int) Math.ceil((amount*0.25))));
        }
        return damageAmount;
    }
    @Override
    public void updateDescription() {
        amount2 = (int) Math.ceil((amount*0.25));
        description = DESCRIPTIONS[0] + amount2 + DESCRIPTIONS[1] + amount2 + DESCRIPTIONS[2];
    }
    @Override
    public AbstractPower makeCopy() {
        return new Scorchpower(owner, source, amount);
    }

    @Override
    public int getHealthBarAmount() {
      return Math.max(amount-owner.currentBlock,0);
    }

    @Override
    public Color getColor() {
        return CardHelper.getColor(209,107,4);
    }
    public float[] _lightsOutGetXYRI() {
        return new float[] {owner.hb.cX, owner.hb.cY, (float)(owner.hb.width+(0.01*amount)), 0.05f * amount};
    }
    public Color[] _lightsOutGetColor() {
        return new Color[] {Color.SCARLET.cpy()};
    }

}
