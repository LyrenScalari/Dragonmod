package dragonmod.cards.Rimedancer.Common;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import dragonmod.actions.ThrowIcicleAction;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.orbs.Icicle;
import dragonmod.powers.Rimedancer.Chillpower;
import dragonmod.util.Wiz;

public class RayofFrost extends AbstractRimedancerCard {
    public static final String ID = RayofFrost.class.getSimpleName();
    public RayofFrost(){
        super(ID,1,CardType.ATTACK,CardRarity.COMMON,CardTarget.ENEMY);
        setDamage(10);
        setMagic(3,2);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractOrb o : Wiz.Player().orbs) {
            if (o instanceof Icicle) {
                Wiz.atb(new ThrowIcicleAction(o,m.hb,Color.CYAN));
                Wiz.atb(new ThrowIcicleAction(o,m.hb,Color.CYAN));
                break;
            }
        }
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL));
        Wiz.applyToEnemy(m,new Chillpower(m,p,magicNumber));
    }
}