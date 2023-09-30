package dragonmod.cards.Justicar.common;

import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.damagemods.BindingHelper;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModContainer;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DamageModifiers.HolyDamage;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.powers.Dragonkin.SanctifyPower;
import dragonmod.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public class HolySlash extends AbstractJusticarCard {
    public static final String ID = HolySlash.class.getSimpleName();
    public static List<AbstractDamageModifier> nomods = new ArrayList<>();
    public HolySlash(){
        super(ID,1,CardType.ATTACK,CardRarity.COMMON,CardTarget.ENEMY);
        setDamage(4,2);
        setMagic2(4,2);
        setMagic(4,5);
        splitdamage = true;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DamageAction(m, BindingHelper.makeInfo(new DamageModContainer(this, nomods), p, SecondMagicNumber, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        Wiz.atb(new DamageAction(m, BindingHelper.makeInfo(new DamageModContainer(this, new HolyDamage(true)),
                p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        Wiz.applyToEnemy(m,new SanctifyPower(m,p,magicNumber));
    }
}