package dragonmod.powers.Rimedancer.powercards;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dragonmod.DragonMod;
import dragonmod.actions.FlourishAction;
import dragonmod.powers.BasePower;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.Wiz;

public class ArcaneCurtainPower extends BasePower {
    public static final String POWER_ID = DragonMod.makeID("ArcaneCurtain");
    @SpireEnum
    public static AbstractCard.CardTags Shiv;
    public ArcaneCurtainPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        updateDescription();
    }
    public void atStartOfTurnPostDraw() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {// 27
            this.flash();// 28

            for(int i = 0; i < this.amount; ++i) {
                Wiz.atb(new FlourishAction());
                Wiz.atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        isDone = true;
                        AbstractCard target = Wiz.Player().drawPile.getTopCard();
                        target.freeToPlayOnce = true;
                        Wiz.Player().drawPile.removeCard(target);
                        target.tags.add(EnchantmentsManager.Sleeved);
                        EnchantmentsManager.addCard(target,true,Wiz.Player());
                    }
                });
            }
        }

    }
    @Override
    public void updateDescription() {
        StringBuilder sb = new StringBuilder();
        if (amount > 1){
            sb.append(DESCRIPTIONS[0]);
            sb.append(amount);
            sb.append(DESCRIPTIONS[2]);
        } else {
            sb.append(DESCRIPTIONS[1]);
        }
        sb.append(DESCRIPTIONS[3]);
        description = sb.toString();
    }
}
