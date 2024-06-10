package dragonmod.powers.Dragonkin;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.powers.BasePower;
import dragonmod.ui.DivineEyeParticle;
import dragonmod.util.Wiz;

import static dragonmod.DragonMod.makeID;

public class ConfessionPower extends BasePower implements CloneablePowerInterface {
    public AbstractCreature source;
    public static final String POWER_ID = makeID("Confession");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public ConfessionPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        updateDescription();
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        CardCrawlGame.sound.play("POWER_MANTRA", 0.05F);
        if (AbstractDungeon.actionManager.turnHasEnded){
            Wiz.att(new ReducePowerAction(owner,owner,this,amount/2));
        }
        if (info.owner != Wiz.Player()) {
            Wiz.dmgtop(info.owner,new DamageInfo(Wiz.Player(),amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        } else {
            Wiz.dmgtop(AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true), new DamageInfo(Wiz.Player(),amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        }
        Wiz.att(new AbstractGameAction() {
            @Override
            public void update() {
                CardCrawlGame.sound.play("POWER_MANTRA", 0.05F);
                for (int i = 0; i < AbstractDungeon.miscRng.random(20, 30); ++i) {
                    AbstractDungeon.effectsQueue.add(new DivineEyeParticle());
                }
                isDone = true;
            }
        });
        return damageAmount;
    }
    
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new ConfessionPower(owner, amount);
    }
}