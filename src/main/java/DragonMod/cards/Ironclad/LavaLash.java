package DragonMod.cards.Ironclad;

import DragonMod.cards.AbstractEasyCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import static DragonMod.util.Wiz.*;
import static DragonMod.DragonMod.makeID;

public class LavaLash extends AbstractEasyCard {
    public final static String ID = makeID("LavaLash");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public LavaLash() {
        super(ID, 0, AbstractCard.CardType.ATTACK, CardRarity.COMMON, AbstractCard.CardTarget.ENEMY, CardColor.RED);
        baseDamage = 10;
        baseMagicNumber = magicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        atb(new DamageAction(p,new DamageInfo(p,magicNumber, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    public void upp() {
        upgradeDamage(3);
    }
}
