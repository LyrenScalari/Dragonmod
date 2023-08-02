package dragonmod.cards.Justicar;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.powers.Dragonkin.AshfallPower;

public class Ashfall extends AbstractPrimalCard {

    public static final String ID = Ashfall.class.getSimpleName();
    private static final int COST = 2;
    private static final int UPGRADED_COST = 2;
    private static final int POTENCY = 8;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static final int MAGIC = 8;
    private static final int UPGRADE_MAGIC = 3;

    public Ashfall() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        setMagic(8,3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p,p, new AshfallPower(p,p, magicNumber)));
        super.use(p,m);
    }
}
