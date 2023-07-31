package dragonmod.powers.Drifter;

import basemod.helpers.CardModifierManager;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theDragonkin.CardMods.PlusDamageBlockCardMod;
import theDragonkin.DragonkinMod;

import java.util.ArrayList;

public class SingedOasisPower extends AbstractPower implements onScriedPower, CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = DragonkinMod.makeID("SingedOasis");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SingedOasisPower(final AbstractCreature owner, final AbstractCreature source, int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;

        type = PowerType.BUFF;
        isTurnBased = false;
        this.loadRegion("like_water");

        updateDescription();
    }
    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, Color.GOLDENROD.cpy());
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new SingedOasisPower(owner, source,amount);
    }

    @Override
    public void onCardsScried(ArrayList<AbstractCard> cards) {
        for (AbstractCard c : cards){
            CardModifierManager.addModifier(c,new PlusDamageBlockCardMod(amount));
        }
    }
}
