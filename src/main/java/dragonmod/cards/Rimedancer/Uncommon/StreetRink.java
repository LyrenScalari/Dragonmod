package dragonmod.cards.Rimedancer.Uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import dragonmod.actions.ExploitAction;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.powers.Rimedancer.Chillpower;
import dragonmod.util.Wiz;

public class StreetRink extends AbstractRimedancerCard {
    public static final String ID = StreetRink.class.getSimpleName();

    public StreetRink() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        setMagic(2);
        setBlock(4);
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseBlock = baseBlock;
        AbstractPower Chill = mo.getPower(Chillpower.POWER_ID);
        if (!upgraded){
            if (Chill != null){
                baseBlock += Chill.amount;
            }
        }
        super.calculateCardDamage(mo);
        this.baseBlock = realBaseBlock;
        this.isBlockModified = this.block != this.baseBlock;
    }
    public void applyPowers() {
        int realBaseBlock = baseBlock;
        AbstractPower Chill;
        if (upgraded){
            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters){
                Chill = m.getPower(Chillpower.POWER_ID);
                if (Chill != null){
                    baseBlock += Chill.amount;
                }
            }
        }
        super.applyPowers();
        this.baseBlock = realBaseBlock;
        this.isBlockModified = this.block != this.baseBlock;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.att(new ExploitAction(()->new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                Wiz.block(p,block);
                AbstractPower Chill;
                if (!upgraded){
                    Chill = m.getPower(Chillpower.POWER_ID);
                    Wiz.atb(new ReducePowerAction(m,p,Chill,Chill.amount/2));
                } else {
                    for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters){
                        Chill = mo.getPower(Chillpower.POWER_ID);
                        if (Chill != null){
                            Wiz.atb(new ReducePowerAction(mo,p,Chill,Chill.amount/2));
                        }
                    }
                }
            }
        }, Chillpower.POWER_ID,m));
        if (!upgraded){
            Wiz.applyToEnemy(m,new VulnerablePower(m,magicNumber,false));
        } else {
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters){
                Wiz.applyToEnemy(mo,new VulnerablePower(mo,magicNumber,false));
            }
        }
    }
    @Override
    public void upgrade() {
        this.target = CardTarget.ALL_ENEMY;
        super.upgrade();
    }
}
