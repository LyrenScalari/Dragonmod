package dragonmod.cards.Warden.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Warden.AbstractWardenCard;
import dragonmod.util.Wiz;

public class BlazingEdge extends AbstractWardenCard {
    public static final String ID = BlazingEdge.class.getSimpleName();

    public BlazingEdge() {
        super(ID, -2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        setMagic(4, 2);
        setSelfRetain(true);
    }
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }
    public void triggerWhenDrawn() {
        Wiz.atb(new DamageRandomEnemyAction(new DamageInfo(Wiz.Player(),magicNumber, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        Wiz.atb(new DamageRandomEnemyAction(new DamageInfo(Wiz.Player(),magicNumber, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }

    public void triggerOnExhaust() {
        Wiz.atb(new DamageRandomEnemyAction(new DamageInfo(Wiz.Player(),magicNumber, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        Wiz.atb(new DamageRandomEnemyAction(new DamageInfo(Wiz.Player(),magicNumber, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }
}

