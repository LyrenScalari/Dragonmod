package dragonmod.cards.Justicar;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.powers.Dragonkin.CrusaderFormpower;

public class CrusaderForm extends AbstractHolyCard {
    public static final String ID = DragonMod.makeID(CrusaderForm.class.getSimpleName());
    public CrusaderForm() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.tags.add(BaseModCardTags.FORM);
        setMagic(1);
        setEthereal(true, false);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new CrusaderFormpower(p, p, magicNumber), magicNumber));
        super.use(p,m);
    }
}
