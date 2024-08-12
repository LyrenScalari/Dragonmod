package dragonmod.cards.Justicar.common;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.powers.Dragonkin.InspirationPower;
import dragonmod.util.Wiz;

public class HolyFlash extends AbstractJusticarCard {
    public static final String ID = HolyFlash.class.getSimpleName();
    public HolyFlash(){
        super(ID,1,CardType.ATTACK,CardRarity.COMMON,CardTarget.ENEMY);
        setDamage(8,2);
        setMagic(1);
    }
    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        AbstractPower Zeal = Wiz.Player().getPower(InspirationPower.POWER_ID);
        int ZealBonus;
        if (upgraded) {
            ZealBonus= 2;
        } else ZealBonus =3;
        if (Zeal != null){
            baseDamage += Zeal.amount*ZealBonus;
            if (upgraded){
                magicNumber += (int) Math.floor((double) Zeal.amount/2);
            } else magicNumber += (int) Math.floor((double) Zeal.amount/4);
        }
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }// 79

    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        AbstractPower Zeal = Wiz.Player().getPower(InspirationPower.POWER_ID);
        int ZealBonus;
        if (upgraded) {
            ZealBonus= 2;
        } else ZealBonus =3;
        if (Zeal != null){
            baseDamage += Zeal.amount*ZealBonus;
            if (upgraded){
                magicNumber += (int) Math.floor((double) Zeal.amount/2);
            } else magicNumber += (int) Math.floor((double) Zeal.amount/4);
        }
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL));
    }
}
