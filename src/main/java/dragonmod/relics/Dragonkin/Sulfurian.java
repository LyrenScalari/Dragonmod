package dragonmod.relics.Dragonkin;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.relics.BaseRelic;
import dragonmod.util.TextureLoader;

import static dragonmod.DragonMod.makeID;

public class Sulfurian extends BaseRelic {

    // ID, images, text.
    public static final String ID = makeID(Sulfurian.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(DragonMod.relicPath("Sulfurian.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(DragonMod.relicPath("Sulfurian.png"));
    // You can also have a relic be only usable once per combat. Check out Hubris for more examples, including other StSlib things.

    private boolean isPlayerTurn = false; // We should make sure the relic is only activateable during our turn, not the enemies'.
    private  boolean used = false;

    public Sulfurian() {
        super(ID, "Sulfurian", RelicTier.BOSS, LandingSound.HEAVY);
        tips.clear();
        tips.add(new PowerTip(name, description));
        counter = 1;
    }

    @Override
    public void atPreBattle() {
    }

    @Override
    public void onCardDraw(AbstractCard card){
    }

    @Override
    public void onUseCard(final AbstractCard c , final UseCardAction ca){
    }

    @Override
    public void atTurnStart(){
        used = false;
    }

    @Override
    public void wasHPLost(int damageAmount) {
        addToBot(new MakeTempCardInDiscardAction(new Burn(),counter));
    }

    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }
    @Override
    public void onPlayerEndTurn() {
        used = false;
    }

    @Override
    public void onMonsterDeath(AbstractMonster m) {
    }
    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
