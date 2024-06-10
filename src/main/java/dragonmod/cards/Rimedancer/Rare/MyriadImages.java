package dragonmod.cards.Rimedancer.Rare;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import dragonmod.actions.IcicleFanAction;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.orbs.Icicle;
import dragonmod.util.Wiz;

import java.util.ArrayList;

public class MyriadImages extends AbstractRimedancerCard {
    public static final String ID = MyriadImages.class.getSimpleName();
    public MyriadImages(){
        super(ID,0,CardType.ATTACK,CardRarity.RARE,CardTarget.ALL_ENEMY);
        setDamage(50,10);
        setMagic(9);
        setMagic2(9,-1);
    }
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        int tilt = 0;
        for (AbstractCard c : Wiz.Player().hand.group){
            if (Wiz.Player().hand.group.indexOf(c) > Wiz.Player().hand.group.indexOf(this)){
                tilt += 1;
            }
        }
        if (tilt >= magicNumber){
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        int tilt = 0;
        for (AbstractCard c : Wiz.Player().hand.group){
            if (Wiz.Player().hand.group.indexOf(c) > Wiz.Player().hand.group.indexOf(this)){
                tilt += 1;
            }
        }
        if (!canUse) {
            return false;
        } else return (tilt >= magicNumber);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int tilt = 0;
        for (AbstractCard c : Wiz.Player().hand.group){
            if (Wiz.Player().hand.group.indexOf(c) > Wiz.Player().hand.group.indexOf(this)){
                tilt += 1;
            }
        }
        if (tilt >= SecondMagicNumber){
            Icicle tothrow = null;
            for (AbstractOrb o : Wiz.Player().orbs) {
                if (o instanceof Icicle) {
                    tothrow = (Icicle) o;
                }
            }
            ArrayList<Hitbox> hbs = new ArrayList<>();
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters){
                hbs.add(mo.hb);
            }
            Wiz.vfx(new WhirlwindEffect());
            Wiz.vfx(new BlizzardEffect(baseDamage,false));
            Wiz.atb(new IcicleFanAction(tothrow, hbs, Color.CYAN));
            Wiz.atb(new DamageAllEnemiesAction(p,baseDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters){
                Wiz.applyToEnemyTemp(mo,new StrengthPower(mo,-magicNumber));
            }
        }
    }
}
