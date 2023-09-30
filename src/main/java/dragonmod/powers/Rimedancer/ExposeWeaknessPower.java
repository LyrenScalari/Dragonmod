package dragonmod.powers.Rimedancer;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.watcher.MantraPower;
import dragonmod.DragonMod;
import dragonmod.powers.BasePower;
import dragonmod.util.Wiz;

public class ExposeWeaknessPower extends BasePower implements CloneablePowerInterface {
    public AbstractCreature source;
    public boolean used = false;
    private static AbstractPower powerToLose = new MantraPower(null,0);
    public static final String POWER_ID = DragonMod.makeID("ExposeWeakness");
    public ExposeWeaknessPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(POWER_ID, NeutralPowertypePatch.NEUTRAL,false,owner,source, amount);
        priority = 70;
        this.img = powerToLose.img;
        this.region48 = powerToLose.region48;
        this.region128 = powerToLose.region128;
        this.loadRegion("mantra");
        updateDescription();
    }
    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, Color.SCARLET.cpy());
    }
    public int onAttacked(DamageInfo info, int damageAmount) {
        Wiz.applyToEnemy((AbstractMonster) owner,new VulnerablePower(owner,amount,false));
        Wiz.applyToEnemy((AbstractMonster) owner,new MarkPower(owner, AbstractDungeon.player,amount));
        return damageAmount;// 52
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    @Override
    public AbstractPower makeCopy() {
        return new  ExposeWeaknessPower(owner, source, amount);
    }

}