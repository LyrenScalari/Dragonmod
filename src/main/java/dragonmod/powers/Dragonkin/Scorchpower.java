package dragonmod.powers.Dragonkin;


import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import dragonmod.DragonMod;
import dragonmod.patches.ScorchPatches;
import dragonmod.powers.BasePower;
import dragonmod.relics.Dragonkin.MukySludge;
import dragonmod.ui.TextureLoader;
import dragonmod.util.Wiz;

public class Scorchpower extends BasePower implements CloneablePowerInterface, HealthBarRenderPower, ScorchPatches.StartofTurnPreBlock {
    public AbstractCreature source;

    public static final String POWER_ID = DragonMod.makeID("Scorch");
    private static final Texture tex84 = TextureLoader.getTexture(DragonMod.powerPath("Scorch.png"));
    private static final Texture tex32 = TextureLoader.getTexture(DragonMod.powerPath("Scorch.png"));

    public Scorchpower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(POWER_ID,PowerType.DEBUFF,false,owner,source, amount);
        img = tex84;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        updateDescription();
    }
    public void atStartOfTurn() {
        //Wiz.dmg(owner,new DamageInfo(owner,amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE);
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
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    @Override
    public AbstractPower makeCopy() {
        return new Scorchpower(owner, source, amount);
    }

    @Override
    public int getHealthBarAmount() {
      return amount-owner.currentBlock;
    }

    @Override
    public Color getColor() {
        return CardHelper.getColor(209,107,4);
    }
    public float[] _lightsOutGetXYRI() {
        return new float[] {owner.hb.cX, owner.hb.cY, (float)(owner.hb.width+(0.01*amount)), 0.05f * amount};
    }

    public Color[] _lightsOutGetColor() {
        return new Color[] {Color.SCARLET.cpy()};
    }

    @Override
    public void atStartofTurnPreBlock() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flashWithoutSound();
            Wiz.dmg(owner,new DamageInfo(owner,amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE);
            Wiz.atb(new ReducePowerAction(owner,owner,this,1));
        }
    }
}
