package dragonmod.cards.Warden.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import dragonmod.actions.BanishFromExhaustAction;
import dragonmod.cards.Warden.AbstractWardenCard;
import dragonmod.util.Wiz;

public class YinYangTalisman extends AbstractWardenCard {
    public static final String ID = YinYangTalisman.class.getSimpleName();
    public YinYangTalisman(){
        super(ID,2,CardType.SKILL,CardRarity.UNCOMMON,CardTarget.SELF);
        setBlock(12,3);
        setMagic(1,1);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.block(p,block);
        Wiz.atb(new BanishFromExhaustAction(1, ()->new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                Wiz.applyToSelf(new StrengthPower(p,magicNumber));
            }
        }));
    }
}
