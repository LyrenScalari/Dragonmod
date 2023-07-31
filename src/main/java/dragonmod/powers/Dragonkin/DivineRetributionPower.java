package dragonmod.powers.Dragonkin;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DragonMod;
import dragonmod.actions.SmiteAction;
import dragonmod.powers.BasePower;
import dragonmod.util.ReciveDamageEffect;


public class DivineRetributionPower extends BasePower implements CloneablePowerInterface, ReciveDamageEffect {
    public AbstractCreature source;
    public static final String POWER_ID = DragonMod.makeID("DivineRetribution");
    public DivineRetributionPower(final AbstractCreature owner, final AbstractCreature source,int dmgamount) {
        super(POWER_ID,PowerType.BUFF,false,owner,source, dmgamount);
        this.loadRegion("defenseNext");
        updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (amount > 0) {
            CardCrawlGame.sound.play("POWER_MANTRA", 0.05F);
            AbstractMonster m = AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster) null, true, AbstractDungeon.cardRandomRng);
            addToBot(new SmiteAction(m, new DamageInfo(owner, amount, DamageInfo.DamageType.THORNS)));
            amount = 0;
        }
    }
    public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
        FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(this.amount), x, y, this.fontScale, c);
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    @Override
    public AbstractPower makeCopy() {
        return new DivineRetributionPower(owner, source,amount);
    }
    @Override
    public void onReciveDamage(int damage) {
        if (!AbstractDungeon.actionManager.turnHasEnded || (!DragonMod.Seals.isEmpty() && !DragonMod.damagetaken)){
         amount += damage*2;
        }
    }
}
