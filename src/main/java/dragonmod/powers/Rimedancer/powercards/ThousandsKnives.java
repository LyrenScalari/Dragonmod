package dragonmod.powers.Rimedancer.powercards;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dragonmod.DragonMod;
import dragonmod.powers.BasePower;
import dragonmod.util.Wiz;

public class ThousandsKnives extends BasePower {
    public static final String POWER_ID = DragonMod.makeID("ThousandsKnives");
    @SpireEnum
    public static AbstractCard.CardTags Shiv;
    public ThousandsKnives(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        updateDescription();
    }
    public void atStartOfTurnPostDraw() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
           onSpecificTrigger();
        }
    }
    public void onSpecificTrigger() {
        this.flash();
        Wiz.atb(new MakeTempCardInHandAction(new Shiv(),amount));
    }

    @Override
    public void updateDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(DESCRIPTIONS[0]);
        if (amount > 1){
            sb.append(DESCRIPTIONS[2]);
            sb.append(amount);
            sb.append(DESCRIPTIONS[3]);
        } else {
            sb.append(DESCRIPTIONS[1]);
        }
        sb.append(DESCRIPTIONS[4]);
        description = sb.toString();
    }
}
