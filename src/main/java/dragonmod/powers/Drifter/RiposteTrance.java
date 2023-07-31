package dragonmod.powers.Drifter;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnMyBlockBrokenPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DragonMod;
import dragonmod.patches.FieldsManager;
import dragonmod.powers.BasePower;
import dragonmod.util.Wiz;

public class RiposteTrance extends BasePower implements CloneablePowerInterface, OnMyBlockBrokenPower {
    public AbstractCreature source;

    public static final String POWER_ID = DragonMod.makeID("RiposteTrance");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public AbstractMonster lastattacker;
    public RiposteTrance(final AbstractCreature owner, final AbstractCreature source, int amount) {
        super(POWER_ID,PowerType.BUFF,false,owner,source, amount);
        this.loadRegion("flight");
        updateDescription();
    }

    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, Color.SKY.cpy());
    }


    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }


    public AbstractPower makeCopy() {
        return new RiposteTrance(owner, source,amount);
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        lastattacker = (AbstractMonster) info.owner;
        return damageAmount;
    }


    public void onMyBlockBroken() {
        if (lastattacker != null){
            for (int i = 0; i < amount; i++){
                addToBot(new AbstractGameAction() {
                    @Override
                    public void update() {
                        AbstractCard Trance = new Trance();
                        Trance.applyPowers();
                        Trance.calculateCardDamage(lastattacker);
                        Trance.use(AbstractDungeon.player,lastattacker);
                        FieldsManager.addCard(Trance,true,lastattacker);
                        isDone = true;
                    }
                });
            }
            Wiz.atb(new RemoveSpecificPowerAction(owner,owner,this));
        }
    }
}
