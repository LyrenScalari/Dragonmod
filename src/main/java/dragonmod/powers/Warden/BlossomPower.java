package dragonmod.powers.Warden;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.PetalEffect;
import dragonmod.DragonMod;
import dragonmod.powers.BasePower;
import dragonmod.ui.TextureLoader;
import dragonmod.util.Wiz;

public class BlossomPower extends BasePower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = DragonMod.makeID("Blossom");
    private static final Texture tex84 = TextureLoader.getTexture(DragonMod.powerPath("Pursuit.png"));
    private static final Texture tex32 = TextureLoader.getTexture(DragonMod.powerPath("Pursuit.png"));
    private AbstractMonster Target;
    public BlossomPower() {
        super(POWER_ID,PowerType.BUFF,false,Wiz.Player(),Wiz.Player(), -1);
        img = tex84;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        updateDescription();
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
    public void onAfterCardPlayed(AbstractCard card) {
        if (card.type == AbstractCard.CardType.SKILL){
            this.flash();
            Wiz.atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    AbstractDungeon.effectsQueue.add(new PetalEffect());
                    AbstractDungeon.effectsQueue.add(new PetalEffect());
                    AbstractDungeon.effectsQueue.add(new PetalEffect());
                    AbstractDungeon.effectsQueue.add(new PetalEffect());
                    AbstractDungeon.effectsQueue.add(new PetalEffect());
                    AbstractDungeon.effectsQueue.add(new PetalEffect());
                }
            });
            Wiz.atb(new GainEnergyAction(1));
            Wiz.atb(new RemoveSpecificPowerAction(owner,owner,this));
        }
    }
    public void atStartOfTurn() {
        this.addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }
    @Override
    public AbstractPower makeCopy() {
        return new BlossomPower();
    }
}