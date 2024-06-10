package dragonmod.powers.Rimedancer;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.LockOnPower;
import dragonmod.DragonMod;
import dragonmod.powers.BasePower;
import dragonmod.util.Wiz;

public class SilverShadowPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = DragonMod.makeID("SilverShadow");
    @Override
    public AbstractPower makeCopy() {
        return new SilverShadowPower(owner,amount);
    }
    public SilverShadowPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, Wiz.Player(),amount);
        updateDescription();
    }
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != this.owner) {
            this.flash();
            Wiz.applyToEnemy((AbstractMonster) info.owner,new LockOnPower(info.owner,amount));
        }
        return damageAmount;// 49
    }

    public void atStartOfTurn() {
        this.addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, this));
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}