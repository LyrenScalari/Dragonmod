package dragonmod.cards.Justicar.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.util.HymnManager;
import dragonmod.util.Wiz;

public class Dawnlight extends AbstractJusticarCard {
    public static final String ID = Dawnlight.class.getSimpleName();
    public Dawnlight(){
        super(ID,1,CardType.ATTACK,CardRarity.COMMON,CardTarget.ALL);
        setDamage(2,1);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DamageAllEnemiesAction(p,baseDamage * HymnManager.getDevotion(), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.LIGHTNING));
    }
}
