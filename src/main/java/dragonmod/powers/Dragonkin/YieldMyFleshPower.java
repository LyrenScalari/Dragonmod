package dragonmod.powers.Dragonkin;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DragonMod;
import dragonmod.actions.CureAction;
import dragonmod.interfaces.OnCoda;
import dragonmod.powers.BasePower;
import dragonmod.ui.TextureLoader;
import dragonmod.util.AbstractSeal;
import dragonmod.util.HymnManager;
import dragonmod.util.Wiz;

public class YieldMyFleshPower extends BasePower implements CloneablePowerInterface, OnCoda {
    public AbstractCreature source;

    public static final String POWER_ID = DragonMod.makeID("YieldMyFlesh");
    private static final Texture tex84 = TextureLoader.getTexture(DragonMod.powerPath("Agony.png"));
    private static final Texture tex32 = TextureLoader.getTexture(DragonMod.powerPath("Agony.png"));

    public YieldMyFleshPower(final int amount) {
        super(POWER_ID,PowerType.DEBUFF,false,Wiz.Player(),Wiz.Player(), amount);
        img = tex84;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        updateDescription();
    }
    public void wasHPLost(DamageInfo info, int damageAmount) {
        if (damageAmount > 0 && !AbstractDungeon.actionManager.turnHasEnded) {
            this.flash();
            if(!HymnManager.ActiveVerses.isEmpty()){
                Wiz.atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        isDone = true;
                        ((AbstractSeal)HymnManager.ActiveVerses.get(0)).Chant();
                    }
                });
            }
        }
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    @Override
    public AbstractPower makeCopy() {
        return new Scorchpower(owner, source, amount);
    }

    @Override
    public void triggerOnCoda() {
        Wiz.atb(new CureAction(amount));
    }
}
