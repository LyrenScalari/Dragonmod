package dragonmod.cards.Rimedancer.Starter;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.powers.Rimedancer.Chillpower;
import dragonmod.util.Wiz;


public class ExhaleofHoarfrost extends AbstractRimedancerCard {
    public static final String ID = ExhaleofHoarfrost.class.getSimpleName();
    public ExhaleofHoarfrost(){
        super(ID,2,CardType.ATTACK,CardRarity.BASIC,CardTarget.ALL_ENEMY);
        setDamage(6,3);
        setMagic(3,2);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new VFXAction(new BlizzardEffect(damage,false)));
        Wiz.atb(new DamageAllEnemiesAction(p,baseDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters){
            if (!mo.isDeadOrEscaped()) {
                Wiz.applyToEnemy(mo,new Chillpower(mo,p,magicNumber));
            }
        }
    }
}
