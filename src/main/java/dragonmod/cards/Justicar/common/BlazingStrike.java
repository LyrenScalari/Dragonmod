package dragonmod.cards.Justicar.common;

import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.util.Wiz;

public class BlazingStrike extends AbstractJusticarCard {
    public static final String ID = BlazingStrike.class.getSimpleName();
    public BlazingStrike(){
        super(ID,1,CardType.ATTACK,CardRarity.COMMON,CardTarget.ENEMY);
        setDamage(8,4);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL));
        AbstractCard neighbor = DragonMod.getLeftCard(this);
        Wiz.atb(new DiscardSpecificCardAction(neighbor));
    }
}