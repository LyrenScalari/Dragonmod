package dragonmod.cards.Rimedancer.Uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.powers.Rimedancer.BleedPower;
import dragonmod.powers.Rimedancer.Chillpower;
import dragonmod.util.Wiz;

public class HowlingBlast extends AbstractRimedancerCard {
    public static final String ID = HowlingBlast.class.getSimpleName();

    public HowlingBlast() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        setDamage(11, 3);
        setMagic(4);
    }
    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        AbstractPower Zeal = mo.getPower(Chillpower.POWER_ID);
        int ZealBonus;
        if (upgraded) {
            ZealBonus= 1;
        } else ZealBonus =2;
        if (Zeal != null){
            baseDamage += Zeal.amount*ZealBonus;
        }
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.vfx(new WhirlwindEffect());
        AbstractPower chill = m.getPower(Chillpower.POWER_ID);
        int chillcount = 0;
        if (chill != null) {
            if (upgraded) {
                chillcount = chill.amount * 2;
            } else chillcount = chill.amount;
        }
        Wiz.vfx(new BlizzardEffect(magicNumber+chillcount,false));
        Wiz.atb(new DamageAction(m,new DamageInfo(p,baseDamage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.POISON));
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters){
            Wiz.applyToEnemy(mo,new BleedPower(mo,magicNumber+chillcount));
        }
    }
}
