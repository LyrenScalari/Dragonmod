package dragonmod.cards.Rimedancer.Common;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import dragonmod.actions.ThrowIcicleAction;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.orbs.Icicle;
import dragonmod.util.Wiz;

public class IcicleSpear extends AbstractRimedancerCard {
    public static final String ID = IcicleSpear.class.getSimpleName();
    public IcicleSpear(){
        super(ID,1,CardType.ATTACK,CardRarity.COMMON,CardTarget.ALL_ENEMY);
        setDamage(7,3);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractOrb o : p.orbs){
            if (o instanceof Icicle){
                Wiz.atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        target = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
                        Wiz.atb(new ThrowIcicleAction(o,target.hb,Color.CYAN));
                        Wiz.dmg(target,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
                        isDone = true;
                    }
                });
            }
        }

    }
}
