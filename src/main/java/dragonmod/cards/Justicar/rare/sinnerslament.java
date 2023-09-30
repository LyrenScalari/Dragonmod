package dragonmod.cards.Justicar.rare;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.util.HymnManager;
import dragonmod.util.Wiz;

public class sinnerslament extends AbstractJusticarCard {

    public static final String ID = sinnerslament.class.getSimpleName();
    public sinnerslament(){
        super(ID,3,CardType.SKILL,CardRarity.RARE,CardTarget.SELF);
        setMagic(6);
        setMagic2(18,-2);
        tags.add(HymnManager.Verse);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
            }
        });
    }
}
