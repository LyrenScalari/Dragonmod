package dragonmod.cards.Justicar;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class Detox extends AbstractJusticarCard {

    public static final String ID =Detox.class.getSimpleName();

    public Detox() {
        super(ID, 2,CardType.SKILL,CardRarity.RARE,CardTarget.ENEMY);
        setMagic(1);
        setEthereal(true,false);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractPower power : m.powers){
            if (power.type == AbstractPower.PowerType.DEBUFF) {
                AbstractDungeon.actionManager.addToBottom(
                        new ApplyPowerAction(p,p, new StrengthPower(p,magicNumber),magicNumber));
                AbstractDungeon.actionManager.addToBottom(
                        new ApplyPowerAction(p,p, new DexterityPower(p,magicNumber),magicNumber));
                if (!this.upgraded) {
                    AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(m, p, power, power.amount / 2));
                }
            }
        }
    }
}