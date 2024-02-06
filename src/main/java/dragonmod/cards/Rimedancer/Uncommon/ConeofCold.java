package dragonmod.cards.Rimedancer.Uncommon;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import dragonmod.DragonMod;
import dragonmod.actions.IcicleFanAction;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.orbs.Icicle;
import dragonmod.ui.TextureLoader;
import dragonmod.util.Wiz;

import java.util.ArrayList;

public class ConeofCold extends AbstractRimedancerCard {
    public static final String ID = ConeofCold.class.getSimpleName();

    public ConeofCold() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        setDamage(4, 2);
        setMagic(1,1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Icicle tothrow = null;
        for (AbstractOrb o : Wiz.Player().orbs) {
            if (o instanceof Icicle) {
                tothrow = (Icicle) o;
            }
        }
        ArrayList<Hitbox> hbs = new ArrayList<>();
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            hbs.add(mo.hb);
        }
        for (int i = 0; i < 2; i++){
            if (tothrow != null){
                Wiz.atb(new IcicleFanAction(tothrow, hbs, Color.CYAN));
            } else {
                Wiz.atb(new IcicleFanAction(TextureLoader.getTexture(DragonMod.orbPath("Icicle.png")),1.0f,hbs,Color.CYAN,false));
            }
            Wiz.atb(new DamageAllEnemiesAction(p, baseDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
    }
}