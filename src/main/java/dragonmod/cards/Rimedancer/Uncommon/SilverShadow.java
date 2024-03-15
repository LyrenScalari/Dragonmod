package dragonmod.cards.Rimedancer.Uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.powers.Rimedancer.SilverShadowPower;
import dragonmod.util.Wiz;

public class SilverShadow extends AbstractRimedancerCard {
    public static final String ID = SilverShadow.class.getSimpleName();
    public SilverShadow(){
        super(ID,1,CardType.ATTACK,CardRarity.UNCOMMON,CardTarget.ENEMY);
        setDamage(7,3);
        setMagic(2);
    }
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        int tilt = 0;
        for (AbstractCard c : Wiz.Player().hand.group){
            if (Wiz.Player().hand.group.indexOf(c) > Wiz.Player().hand.group.indexOf(this)){
                tilt += 1;
            }
        }
        if (tilt >= 3){
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY);
        int tilt = 0;
        for (AbstractCard c : Wiz.Player().hand.group){
            if (Wiz.Player().hand.group.indexOf(c) > Wiz.Player().hand.group.indexOf(this)){
                tilt += 1;
            }
        }
        if (tilt >= magicNumber){
            Wiz.applyToSelf(new SilverShadowPower(p,magicNumber));
        }
    }
}