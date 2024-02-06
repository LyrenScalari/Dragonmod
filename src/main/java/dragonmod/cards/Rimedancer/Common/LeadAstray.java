package dragonmod.cards.Rimedancer.Common;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.FlickCoinEffect;
import dragonmod.actions.ExploitAction;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.powers.Rimedancer.Chillpower;
import dragonmod.util.Wiz;

public class LeadAstray extends AbstractRimedancerCard {

    public static final String ID = LeadAstray.class.getSimpleName();
    public LeadAstray(){
        super(ID,1,CardType.SKILL,CardRarity.COMMON,CardTarget.ENEMY);
        setMagic(2);
        setBlock(7);
        setMagic2(2,2);
    }
    public float[] _lightsOutGetXYRI() {
        return new float[] {hb.x, hb.y, 100f, 1.25f};
    }

    public Color[] _lightsOutGetColor() {
        return new Color[] {Color.SKY.cpy()};
    }
    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.vfx(new FlickCoinEffect(p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY));
        Wiz.applyToEnemy(m,new WeakPower(m,magicNumber,false));
        Wiz.block(p,block);
        Wiz.atb(new ExploitAction(()->new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                Wiz.applyToEnemy(m,new Chillpower(m,p,SecondMagicNumber));
            }
        },2,m));
    }
}