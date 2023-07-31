package dragonmod.relics.Dragonkin;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.PotionSlot;
import com.megacrit.cardcrawl.relics.Sozu;
import com.megacrit.cardcrawl.vfx.ObtainPotionEffect;
import dragonmod.DragonMod;
import dragonmod.relics.BaseRelic;

import static dragonmod.DragonMod.isPlayerDragon;

public class RoyalSignet extends BaseRelic {
    public static final String ID = DragonMod.makeID("RoyalSignet");
    public static final String NAME = "RoyalSignet";
    private static final UIStrings DragonAffinity = CardCrawlGame.languagePack.getUIString("theDragonkin:DragonAffinity");
    public RoyalSignet() {
        super(ID, NAME, RelicTier.UNCOMMON, LandingSound.HEAVY);
        tips.clear();
        tips.add(new PowerTip(name, description));
        tips.add(new PowerTip(DragonAffinity.TEXT[0],DragonAffinity.TEXT[1]));
    }

    @Override
    public void onEquip() {
        AbstractDungeon.player.gainGold(50);
        AbstractDungeon.player.potionSlots = AbstractDungeon.player.potionSlots + 1;
        AbstractDungeon.player.potions.add(new PotionSlot(AbstractDungeon.player.potionSlots - 1));
        for (AbstractPotion p : AbstractDungeon.player.potions){
            if (p instanceof PotionSlot){
                AbstractDungeon.effectsQueue.add(new ObtainPotionEffect(AbstractDungeon.returnRandomPotion(AbstractPotion.PotionRarity.COMMON,true)));
                break;
            }
        }
    }
    public boolean canSpawn() {
        return !AbstractDungeon.player.hasRelic(Sozu.ID) && isPlayerDragon();
    }
    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}