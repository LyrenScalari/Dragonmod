package dragonmod.cards.Justicar.common;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import dragonmod.DragonMod;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.powers.Dragonkin.InspirationPower;
import dragonmod.util.Wiz;

public class SparkingSerenade extends AbstractJusticarCard {
    public static final String ID = SparkingSerenade.class.getSimpleName();
    public SparkingSerenade(){
        super(ID,1,CardType.SKILL,CardRarity.COMMON,CardTarget.SELF);
        setBlock(6,2);
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
        int realBaseBlock = baseBlock;
        AbstractPower Zeal = Wiz.Player().getPower(InspirationPower.POWER_ID);
        int ZealBonus;
        if (upgraded) {
            ZealBonus= 1;
        } else ZealBonus =2;
        if (Zeal != null){
            baseBlock += Zeal.amount*ZealBonus;
        }

        super.applyPowers();
        this.baseBlock = realBaseBlock;
        this.isBlockModified = this.block != this.baseBlock;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.block(p,block);
        AbstractCard neighbor = DragonMod.getLeftCard(this);
        Wiz.atb(new DiscardSpecificCardAction(neighbor));
        if (neighbor != null){
            int cardcost = 0;
            if (neighbor.cost > 0){
                cardcost = neighbor.cost;
            }
            if (neighbor.cost == -2){
                cardcost =EnergyPanel.getCurrentEnergy();
            }
            if (cardcost > 0){
                if (upgraded){
                    cardcost *= 2;
                }
                Wiz.atb(new ApplyPowerAction(p,p,new InspirationPower(p,p, cardcost)));
            }

        }
    }
}
