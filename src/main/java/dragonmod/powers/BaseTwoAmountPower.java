package dragonmod.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;

public abstract class BaseTwoAmountPower extends BasePower {
    public int amount2 = 0;
    public boolean canGoNegative2 = false;
    protected Color redColor2;
    protected Color greenColor2;

    public BaseTwoAmountPower(String ID, PowerType type, boolean isTurnBased, AbstractCreature owner,AbstractCreature source, int amount) {
        super(ID,type,isTurnBased,owner,source,amount);
        this.redColor2 = Color.RED.cpy();
        this.greenColor2 = Color.GREEN.cpy();
    }

    public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
        super.renderAmount(sb, x, y, c);
        if (this.amount2 > 0) {
            if (!this.isTurnBased) {
                this.greenColor2.a = c.a;
                c = this.greenColor2;
            }

            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(this.amount2), x, y + 15.0F * Settings.scale, this.fontScale, c);
        } else if (this.amount2 < 0 && this.canGoNegative2) {
            this.redColor2.a = c.a;
            c = this.redColor2;
            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(this.amount2), x, y + 15.0F * Settings.scale, this.fontScale, c);
        }

    }
}