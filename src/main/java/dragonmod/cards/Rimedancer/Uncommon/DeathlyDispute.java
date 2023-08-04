package dragonmod.cards.Rimedancer.Uncommon;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.cards.Rimedancer.Special.PilferedCrystal;
import dragonmod.util.Wiz;

public class DeathlyDispute extends AbstractRimedancerCard {
    public static final String ID = DeathlyDispute.class.getSimpleName();
    public DeathlyDispute(){
        super(ID,1,CardType.ATTACK,CardRarity.UNCOMMON,CardTarget.ENEMY);
        setDamage(8,4);
        cardsToPreview = new PilferedCrystal();
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL));
        if (m.getIntentBaseDmg() <= 0){
            Wiz.atb(new MakeTempCardInDrawPileAction(cardsToPreview.makeStatEquivalentCopy(),1,true,false));
        }
    }
    @Override
    public void upgrade() {
        if (!upgraded) {
            cardsToPreview.upgrade();
            super.upgrade();
        }
    }
}
