package dragonmod.cards.Rimedancer.Uncommon;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import dragonmod.DamageModifiers.RangedDamage;
import dragonmod.DragonMod;
import dragonmod.actions.GainCrystalOrbSlotAction;
import dragonmod.actions.ThrowIcicleAction;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.orbs.Icicle;
import dragonmod.ui.TextureLoader;
import dragonmod.util.Wiz;

public class FractalShell extends AbstractRimedancerCard {
    public static final String ID = FractalShell.class.getSimpleName();

    public  FractalShell() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        setDamage(10,2);
        setMagic(2,1);
        DamageModifierManager.addModifier(this,new RangedDamage(true));
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
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL));
        Wiz.atb(new ChannelAction(new Icicle()));
        Wiz.atb(new GainCrystalOrbSlotAction(magicNumber));
    }
}
