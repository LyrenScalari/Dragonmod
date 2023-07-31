package dragonmod.powers.Dragonkin;


import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.actions.common.GainCustomBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DragonMod;
import dragonmod.powers.BaseTwoAmountPower;
import dragonmod.util.TextureLoader;

public class WingsofLight extends BaseTwoAmountPower implements CloneablePowerInterface{
    public AbstractCreature source;

    public static final String POWER_ID = DragonMod.makeID("WingsofLight");
    private static final Texture tex84 = TextureLoader.getTexture(DragonMod.powerPath("DivineArmor.png"));
    private static final Texture tex32 = TextureLoader.getTexture(DragonMod.powerPath("DivineArmor.png"));
    private static final float X_JITTER;
    private static final float Y_JITTER;
    private static final float OFFSET_Y;
    private static AbstractCard srcCard;
    private int ActivationCounter;
    static {
        X_JITTER = 120.0F * Settings.scale;
        Y_JITTER = 120.0F * Settings.scale;
        OFFSET_Y = -50.0F * Settings.scale;
    }
    public WingsofLight(final AbstractCreature owner, final AbstractCreature source,int amt1,int amt2, AbstractCard srccard) {
        super(POWER_ID, PowerType.BUFF,false,owner,source, amt1);
        this.amount2 = amt2;
        srcCard = srccard;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        ActivationCounter = 0;
        updateDescription();
    }
    public void atStartOfTurn() {
        this.ActivationCounter = 0;
    }
    @Override
    public void updateDescription() {
        if (amount < 2){
            description = DESCRIPTIONS[0] + DESCRIPTIONS[1] + amount2 + DESCRIPTIONS[2];
        } else description = DESCRIPTIONS[3] + amount2 + DESCRIPTIONS[4] + DESCRIPTIONS[1] + amount2 + DESCRIPTIONS[2];
    }

    public void onSealBreak(){
        if (ActivationCounter <= this.amount){
            AbstractDungeon.actionManager.addToBottom(new GainCustomBlockAction(srcCard,AbstractDungeon.player,amount2));
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new WingsofLight(owner, source,amount,amount2,srcCard);
    }
}