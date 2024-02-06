package dragonmod.cards.Rimedancer.Common;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import dragonmod.DragonMod;
import dragonmod.actions.ThrowIcicleAction;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.orbs.Icicle;
import dragonmod.ui.TextureLoader;
import dragonmod.util.Wiz;

public class SnowflakeChakram extends AbstractRimedancerCard {
    public static final String ID = SnowflakeChakram.class.getSimpleName();
    public SnowflakeChakram(){
        super(ID,0,CardType.ATTACK,CardRarity.COMMON,CardTarget.ENEMY);
        setDamage(4,2);
        setMagic(2,1);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Icicle tothrow = null;
        for (AbstractOrb o : Wiz.Player().orbs) {
            if (o instanceof Icicle) {
                tothrow = (Icicle) o;
                break;
            }
        }
        if (tothrow != null){
            Wiz.atb(new ThrowIcicleAction(tothrow, m.hb, Color.CYAN));
        } else {
            Wiz.atb(new ThrowIcicleAction(TextureLoader.getTexture(DragonMod.orbPath("Icicle.png")),1.0f,m.hb,Color.CYAN));
        }
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        Wiz.atb(new ChannelAction(new Icicle()));
    }
}
