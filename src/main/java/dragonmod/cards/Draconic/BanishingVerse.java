package dragonmod.cards.Draconic;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import dragonmod.powers.Dragonkin.InspirationPower;
import dragonmod.powers.Warden.BanishPower;
import dragonmod.util.TargetedPetalEffect;
import dragonmod.util.Wiz;

public class BanishingVerse extends AbstractDraconicCard {
    public static final String ID = BanishingVerse.class.getSimpleName();
    public BanishingVerse(){
        super(ID,0,CardType.ATTACK,CardRarity.SPECIAL,CardTarget.ENEMY);
        setDamage(3,2);
        setMagic(0,1);
        setExhaust(true);
    }
    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        AbstractPower Zeal = Wiz.Player().getPower(InspirationPower.POWER_ID);
        float ZealBonus;
        if (upgraded) {
            ZealBonus= 0.5F;
        } else ZealBonus =1.25f;
        if (Zeal != null){
            baseDamage += (int) (Zeal.amount*ZealBonus);
        }
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }// 79

    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        AbstractPower Banish = Wiz.Player().getPower(BanishPower.POWER_ID);
        float BanishBonus;
        if (upgraded) {
            BanishBonus= 0.5f;
        } else BanishBonus =1.25f;
        if (Banish != null){
            baseDamage += (int) (Banish.amount*BanishBonus);
        }
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.vfx(new LightningEffect(m.hb.cX,m.hb.cY));
        for (int i =0; i < baseDamage; i++){
            Wiz.vfx(new TargetedPetalEffect(m.hb.cX,m.hb.cY));
            Wiz.vfx(new TargetedPetalEffect(m.hb.cX,m.hb.cY));
        }
        Wiz.dmg(m,new DamageInfo(p,baseDamage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.POISON);
        if (upgraded){
            Wiz.applyToSelf(new BanishPower(p,p,magicNumber));
        }
    }
}