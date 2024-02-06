package dragonmod.cards.Rimedancer.Uncommon;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.util.Wiz;

public class SilverShadow extends AbstractRimedancerCard {
    public static final String ID = SilverShadow.class.getSimpleName();
    public SilverShadow(){
        super(ID,1,CardType.ATTACK,CardRarity.UNCOMMON,CardTarget.ENEMY);
        setDamage(7,3);
        setMagic(2);
    }
    public void triggerOnGlowCheck() {
        boolean frozen = false;
        for (AbstractCard c : Wiz.Player().hand.group){
            if (CardModifierManager.hasModifier(c,"FrozenMod")){
                frozen = true;
            }
        }
        if (frozen){
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY);
        boolean frozen = false;
        for (AbstractCard c : p.hand.group){
            if (CardModifierManager.hasModifier(c,"FrozenMod")){
                frozen = true;
            }
        }
        if (frozen){
            Wiz.applyToSelf(new dragonmod.powers.Rimedancer.SilverShadow(p,magicNumber));
        }
    }
}