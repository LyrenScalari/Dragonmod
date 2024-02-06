package dragonmod.cards.Justicar.common;

import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.actions.SmiteAction;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.powers.Dragonkin.ConfessionPower;
import dragonmod.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public class HolySlash extends AbstractJusticarCard {
    public static final String ID = HolySlash.class.getSimpleName();
    public static List<AbstractDamageModifier> nomods = new ArrayList<>();
    public HolySlash(){
        super(ID,1,CardType.ATTACK,CardRarity.COMMON,CardTarget.ENEMY);
        setDamage(4,2);
        setMagic(4,5);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY);
        Wiz.atb(new SmiteAction(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL)));
        Wiz.applyToSelf(new ConfessionPower(Wiz.Player(),magicNumber));
    }
}