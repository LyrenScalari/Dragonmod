package dragonmod.cards.Warden.common;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DamageModifiers.DarkDamage;
import dragonmod.cards.Warden.AbstractWardenCard;
import dragonmod.powers.Warden.HexPower;
import dragonmod.util.Wiz;

public class DarkStrike extends AbstractWardenCard {
    public static final String ID = DarkStrike.class.getSimpleName();
    public DarkStrike(){
        super(ID,1,CardType.ATTACK,CardRarity.COMMON,CardTarget.ENEMY);
        setDamage(6,3);
        setMagic(6,3);
        DamageModifierManager.addModifier(this, new DarkDamage(true));
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL));
        Wiz.applyToEnemy(m,new HexPower(m,p,magicNumber));
    }
}
