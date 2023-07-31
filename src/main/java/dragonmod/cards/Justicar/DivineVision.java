package dragonmod.cards.Justicar;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.powers.Dragonkin.DivineConvictionpower;

public class DivineVision extends AbstractHolyCard {
    public static final String ID = DragonMod.makeID(DivineVision.class.getSimpleName());
    public DivineVision() {
        super(ID,2,CardType.POWER,CardRarity.UNCOMMON,CardTarget.SELF);
        setMagic(2,1);
    }
    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,m, new DivineConvictionpower(p,m,magicNumber), magicNumber));
        super.use(p,m);
    }
}