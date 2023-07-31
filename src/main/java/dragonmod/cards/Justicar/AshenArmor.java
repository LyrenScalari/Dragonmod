package dragonmod.cards.Justicar;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.powers.Dragonkin.AshenArmorpower;


public class AshenArmor extends AbstractPrimalCard {
    public static final String ID = DragonMod.makeID(AshenArmor.class.getSimpleName());
    public AshenArmor() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        setCostUpgrade(1);
        cardsToPreview = new MoltenEmber();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(p,p, new AshenArmorpower(p,p)));
        super.use(p,m);
    }

}