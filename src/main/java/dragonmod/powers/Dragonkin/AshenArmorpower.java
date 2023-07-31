package dragonmod.powers.Dragonkin;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.actions.CycleAction;
import dragonmod.cards.Justicar.MoltenEmber;
import dragonmod.powers.BasePower;
import dragonmod.util.TextureLoader;

import static dragonmod.DragonMod.makeID;
import static dragonmod.DragonMod.powerPath;

public class AshenArmorpower extends BasePower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = makeID("AshenArmor");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(powerPath("AshArmor.png"));
    private static final Texture tex32 = TextureLoader.getTexture(powerPath("AshArmor.png"));

    public AshenArmorpower(final AbstractCreature owner, final AbstractCreature source) {
        super(POWER_ID,PowerType.BUFF,false,owner,source, -1);
        priority = 105;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void onCardDraw(AbstractCard c){
        if (c.type == AbstractCard.CardType.STATUS){
            this.flash();
            if (AbstractDungeon.player.hand.contains(c)){
                addToBot(new CycleAction(c,1,new MoltenEmber()));
            }
        }
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + DESCRIPTIONS[1];
    }
    @Override
    public AbstractPower makeCopy() {
        return new AshenArmorpower(owner, source);
    }
}

