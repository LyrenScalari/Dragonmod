package dragonmod.powers.Warden;

import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.damagemods.BindingHelper;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModContainer;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import dragonmod.DamageModifiers.DarkDamage;
import dragonmod.DragonMod;
import dragonmod.powers.BaseTwoAmountPower;
import dragonmod.util.Wiz;

public class HexPower extends BaseTwoAmountPower implements CloneablePowerInterface {
    public AbstractCreature source;
    public static final String POWER_ID = DragonMod.makeID("Hex");
    public int damagecount;
    public HexPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(POWER_ID,PowerType.DEBUFF,false,owner,source, amount);
        amount2 = 3;
        damagecount = amount/3;
        updateDescription();
    }
    @Override
    public void updateDescription() {
        int damageval = damagecount;
        if (owner.hasPower(WeakPower.POWER_ID)){
            damageval = (int) (damagecount * 1.5);
        }
        description = DESCRIPTIONS[0] + damageval + DESCRIPTIONS[1] + damagecount + DESCRIPTIONS[2] + amount2 + DESCRIPTIONS[3];
    }
    public void stackPower(int stackAmount) {
        amount += stackAmount;
        amount2 = 3;
        damagecount = amount/3;
    }
    @Override
    public AbstractPower makeCopy() {
        return new HexPower(owner, source, amount);
    }
    public void atStartOfTurn() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flashWithoutSound();
            int damageval = damagecount;
            DamageModContainer DarkDamage = new DamageModContainer(this,new DarkDamage(true));
            if (owner.hasPower(WeakPower.POWER_ID)){
                damageval = (int) (damagecount * 1.5);
            }
            Wiz.dmg(owner, BindingHelper.makeInfo(DarkDamage,owner,damageval, DamageInfo.DamageType.THORNS));
            Wiz.atb(new ReducePowerAction(owner,owner,this,damageval));
            amount2 -= 1;
        }
    }
}
