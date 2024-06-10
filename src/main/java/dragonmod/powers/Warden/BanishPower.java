package dragonmod.powers.Warden;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DragonMod;
import dragonmod.cards.Draconic.BanishingVerse;
import dragonmod.powers.BasePower;
import dragonmod.util.Wiz;

public class BanishPower extends BasePower implements CloneablePowerInterface {
    public AbstractCreature source;
    public static final String POWER_ID = DragonMod.makeID("Banish");
    public BanishPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(POWER_ID,PowerType.DEBUFF,false,owner,source, amount);
        updateDescription();
    }
    @Override
    public void updateDescription() {
        if (amount%5 == 0){
            description = DESCRIPTIONS[0] + "#b"+5  + DESCRIPTIONS[1];
        } else description = DESCRIPTIONS[0] + amount%5  + DESCRIPTIONS[1];

    }
    public void stackPower(int stackAmount) {
        amount += stackAmount;
        if (amount % 5 == 0){
            Wiz.atb(new MakeTempCardInDrawPileAction(new BanishingVerse(),1,true,false));
        }
    }
    @Override
    public AbstractPower makeCopy() {
        return new BanishPower(owner, source, amount);
    }
}