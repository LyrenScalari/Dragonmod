package dragonmod.cards.Justicar.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.orbs.Verses.Wrath;
import dragonmod.util.HymnManager;
import dragonmod.util.Wiz;

public class PrayerofWrath extends AbstractJusticarCard {

    public static final String ID = PrayerofWrath.class.getSimpleName();
    public PrayerofWrath(){
        super(ID,2,CardType.ATTACK,CardRarity.COMMON,CardTarget.SELF);
        setMagic(3);
        setMagic2(2,1);
        setDamage(12,3);
        tags.add(HymnManager.Verse);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                HymnManager.addVerse(new Wrath(damage,magicNumber), PrayerofWrath.this);
                isDone = true;
            }
        });
    }
}
