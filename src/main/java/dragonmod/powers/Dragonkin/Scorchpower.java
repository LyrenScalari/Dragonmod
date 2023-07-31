package dragonmod.powers.Dragonkin;


import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DragonMod;
import dragonmod.powers.BasePower;
import dragonmod.util.TextureLoader;

public class Scorchpower extends BasePower implements CloneablePowerInterface, HealthBarRenderPower {
    public AbstractCreature source;

    public static final String POWER_ID = DragonMod.makeID("Scorch");
    private static final Texture tex84 = TextureLoader.getTexture(DragonMod.powerPath("Scorch.png"));
    private static final Texture tex32 = TextureLoader.getTexture(DragonMod.powerPath("Scorch.png"));

    public Scorchpower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(POWER_ID,PowerType.DEBUFF,false,owner,source, amount);
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public int onAttacked(DamageInfo di, int d){
        if (di.type == DamageInfo.DamageType.NORMAL) {
            this.flash();
            int temp = amount;
            AbstractDungeon.actionManager.addToTop(
                    new ReducePowerAction(this.owner, this.owner, this,1));
            AbstractDungeon.actionManager.addToTop(
                    new DamageAction(owner,new DamageInfo(owner,temp, DamageInfo.DamageType.THORNS)));
            if (AbstractDungeon.player.hasPower(AcidArmorpower.POWER_ID)){
                AbstractDungeon.player.getPower(AcidArmorpower.POWER_ID).onSpecificTrigger();
            }
            return d;
        }
        return d;
    }
    @Override
    public float atDamageFinalGive(float d, DamageInfo.DamageType type){
        if (type == DamageInfo.DamageType.NORMAL && AbstractDungeon.player.hasRelic(MukySludge.ID)) {
            int temp = amount;
            return d - temp;
        }
        return d;
    }
    @Override
    public void onAttack(DamageInfo info, int d, AbstractCreature target){
        if (info.type == DamageInfo.DamageType.NORMAL && AbstractDungeon.player.hasRelic(MukySludge.ID)) {
            this.flash();
            AbstractDungeon.actionManager.addToTop(
                    new ReducePowerAction(this.owner, this.owner, this,1));
        }
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + DESCRIPTIONS[2];
    }
    @Override
    public AbstractPower makeCopy() {
        return new Scorchpower(owner, source, amount);
    }

    @Override
    public int getHealthBarAmount() {
        if (AbstractDungeon.player.hoveredCard != null) {
            if (AbstractDungeon.player.hoveredCard.type == AbstractCard.CardType.ATTACK) {
                if (AbstractDungeon.player.hoveredCard.target == AbstractCard.CardTarget.ENEMY && !(owner.hb.hovered)) {
                    return 0;
                } else return amount;
            }
        }
        return 0;
    }

    @Override
    public Color getColor() {
        return CardHelper.getColor(209,107,4);
    }
}
