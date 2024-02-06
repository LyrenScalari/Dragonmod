package dragonmod.powers.Rimedancer;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DragonMod;
import dragonmod.powers.BasePower;
import dragonmod.util.Wiz;

import java.lang.reflect.Field;

public class BleedPower extends BasePower implements CloneablePowerInterface {
    private AbstractPower powerToLose;
    public static final String POWER_ID = DragonMod.makeID("Bleed");
    private byte moveByte;
    private AbstractMonster.Intent moveIntent;
    private EnemyMoveInfo move = null;
    @Override
    public AbstractPower makeCopy() {
        return new BleedPower(owner,amount);
    }
    public BleedPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, Wiz.Player(),amount);
        updateDescription();
    }

    public void atEndOfRound() {
        flash();
        Wiz.atb(new LoseHPAction(owner,owner,amount));
        addToBot(new RemoveSpecificPowerAction(owner, owner, this.ID));
    }
    public void onInitialApplication() {
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            public void update() {
                if (BleedPower.this.owner instanceof AbstractMonster && BleedPower.this.amount >= BleedPower.this.owner.currentHealth) {
                    moveByte = ((AbstractMonster)BleedPower.this.owner).nextMove;
                    moveIntent = ((AbstractMonster)BleedPower.this.owner).intent;

                    try {
                        Field f = AbstractMonster.class.getDeclaredField("move");
                        f.setAccessible(true);
                        BleedPower.this.move = (EnemyMoveInfo)f.get(BleedPower.this.owner);
                        EnemyMoveInfo stunMove = new EnemyMoveInfo(BleedPower.this.moveByte, DragonMod.BLEEDING_OUT, -1, 0, false);
                        f.set(BleedPower.this.owner, stunMove);
                        ((AbstractMonster)BleedPower.this.owner).createIntent();
                    } catch (NoSuchFieldException | IllegalAccessException var3) {
                        var3.printStackTrace();
                    }
                }

                this.isDone = true;
            }
        });
    }

    public void onRemove() {
        if (this.owner instanceof AbstractMonster && move != null) {
            AbstractMonster m = (AbstractMonster)this.owner;
            m.setMove(this.moveByte, this.moveIntent, this.move.baseDamage, this.move.multiplier, this.move.isMultiDamage);
            m.createIntent();
            m.applyPowers();
        }

    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        if (owner instanceof AbstractMonster && owner.currentHealth <= amount && move == null){
            AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                public void update() {
                        moveByte = ((AbstractMonster)owner).nextMove;
                        moveIntent = ((AbstractMonster)owner).intent;

                        try {
                            Field f = AbstractMonster.class.getDeclaredField("move");
                            f.setAccessible(true);
                            BleedPower.this.move = (EnemyMoveInfo)f.get(BleedPower.this.owner);
                            EnemyMoveInfo stunMove = new EnemyMoveInfo(BleedPower.this.moveByte, DragonMod.BLEEDING_OUT, -1, 0, false);
                            f.set(BleedPower.this.owner, stunMove);
                            ((AbstractMonster)BleedPower.this.owner).createIntent();
                        } catch (NoSuchFieldException | IllegalAccessException var3) {
                            var3.printStackTrace();
                        }
                    this.isDone = true;
                }
            });
        }
    }
}
