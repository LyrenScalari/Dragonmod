package dragonmod.cards.Rimedancer.Common;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import dragonmod.DragonMod;
import dragonmod.actions.ThrowIcicleAction;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.cards.Rimedancer.Special.DazzlingShiv;
import dragonmod.orbs.Icicle;
import dragonmod.ui.IcicleSprayEffect;
import dragonmod.ui.TextureLoader;
import dragonmod.util.Wiz;

public class CrystalBarbs extends AbstractRimedancerCard {
    public static final String ID = CrystalBarbs.class.getSimpleName();
    public CrystalBarbs(){
        super(ID,1,CardType.ATTACK,CardRarity.COMMON,CardTarget.ENEMY);
        setDamage(7,4);
        setMagic(2);
        cardsToPreview = new DazzlingShiv();
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Icicle tothrow = null;
        for (AbstractOrb o : Wiz.adp().orbs) {
            if (o instanceof Icicle) {
                tothrow = (Icicle) o;
            }
        }
        Wiz.vfx(new IcicleSprayEffect(false));
        if (tothrow != null){
            Wiz.atb(new ThrowIcicleAction(tothrow, m.hb, Color.CYAN));
        } else {
            Wiz.atb(new ThrowIcicleAction(TextureLoader.getTexture(DragonMod.orbPath("Icicle.png")),1.0f,m.hb,Color.CYAN));
        }
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL));
        for (int i = 0; i < magicNumber; i++){
                Wiz.atb(new ChannelAction(new Icicle()));
        }
        Wiz.atb(new MakeTempCardInHandAction(cardsToPreview.makeStatEquivalentCopy()));
    }
}