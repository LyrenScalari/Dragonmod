package dragonmod.potions.Dragonkin;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import dragonmod.DragonMod;
import dragonmod.actions.CureAction;
import dragonmod.util.Wiz;

public class NaruuinsGlow extends AbstractPotion {


    public static final String POTION_ID = DragonMod.makeID("NaruuinsGlow");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public NaruuinsGlow() {
        // The bottle shape and inside is determined by potion size and color. The actual colors are the main DragonkinMod.java
        super(NAME, POTION_ID, PotionRarity.RARE, PotionSize.HEART, PotionColor.BLUE);

        // Potency is the damage/magic number equivalent of potions.
        potency = getPotency();
        this.labOutlineColor = DragonMod.JUSTICAR_RED;
        // Initialize the Description
        description = DESCRIPTIONS[0] + potency + DESCRIPTIONS[1];

        // Do you throw this potion at an enemy or do you just consume it.
        isThrown = false;

        // Initialize the on-hover name + description
        tips.add(new PowerTip(name, description));

    }

    @Override
    public void use(AbstractCreature target) {
        target = AbstractDungeon.player;
        // If you are in combat, gain strength and the "lose strength at the end of your turn" power, equal to the potency of this potion.
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            Wiz.atb(new CureAction(potency));
        }
    }

    @Override
    public AbstractPotion makeCopy() {
        return new NaruuinsGlow();
    }

    // This is your potency.
    @Override
    public int getPotency(final int potency) {
        return 23;
    }

    public void upgradePotion()
    {
        potency += 1;
        tips.clear();
        tips.add(new PowerTip(name, description));
    }
}
