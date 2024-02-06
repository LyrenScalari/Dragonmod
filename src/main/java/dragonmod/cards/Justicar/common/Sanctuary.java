package dragonmod.cards.Justicar.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.orbs.Verses.Hallowed;
import dragonmod.util.HymnManager;
import dragonmod.util.Wiz;

public class Sanctuary extends AbstractJusticarCard {

    public static final String ID = Sanctuary.class.getSimpleName();
    public Sanctuary(){
        super(ID,1,CardType.SKILL,CardRarity.COMMON,CardTarget.SELF);
        setMagic(3,2);
        setMagic2(3);
        tags.add(HymnManager.Verse);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                HymnManager.addVerse(new Hallowed(magicNumber),Sanctuary.this);
                isDone = true;
            }
        });
    }
}
