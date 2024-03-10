package dragonmod.cards.Warden.common;

import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.cards.Warden.AbstractWardenCard;
import dragonmod.util.Wiz;

public class ShadowBreak extends AbstractWardenCard {
    public static final String ID = ShadowBreak.class.getSimpleName();
    public ShadowBreak(){
        super(ID,0,CardType.ATTACK,CardRarity.COMMON,CardTarget.ENEMY);
        setDamage(8,4);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard neighbor = DragonMod.getLeftCard(this);
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL));
        Wiz.atb(new ExhaustSpecificCardAction(neighbor,Wiz.hand()));
    }
}
