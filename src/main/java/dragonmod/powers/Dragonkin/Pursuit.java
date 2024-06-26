package dragonmod.powers.Dragonkin;

import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DragonMod;
import dragonmod.powers.BasePower;
import dragonmod.util.Wiz;

public class Pursuit extends BasePower implements CloneablePowerInterface, NonStackablePower {
    public AbstractCreature source;

    public static final String POWER_ID = DragonMod.makeID("Pursuit");
    private AbstractMonster Target;
    public Pursuit(final AbstractCreature owner, final AbstractCreature source, AbstractMonster target) {
        super(POWER_ID,PowerType.BUFF,false,owner,source, -1);
        Target = target;
        updateDescription();
    }
    public void onGainedBlock(float blockAmount) {
        if (blockAmount > 0.0F) {// 27
            this.flash();// 28
            Wiz.dmg(Target,new DamageInfo(owner,(int)blockAmount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        }
    }
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        updateDescription();
        return damage;
    }
        @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
    @Override
    public AbstractPower makeCopy() {
        return new Pursuit(owner, source, Target);
    }
}
