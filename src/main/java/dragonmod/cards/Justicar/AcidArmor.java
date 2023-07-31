package dragonmod.cards.Justicar;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.powers.Dragonkin.AcidArmorpower;


public class AcidArmor extends AbstractPrimalCard {

    public static final String ID = DragonMod.makeID(AcidArmor.class.getSimpleName());

    public AcidArmor() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        setInnate(false,true);
        setMagic(1);
        cardsToPreview = new Burn();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new AcidArmorpower(p, p, magicNumber)));
        super.use(p, m);
    }
}