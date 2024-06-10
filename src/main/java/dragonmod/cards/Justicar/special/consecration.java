package dragonmod.cards.Justicar.special;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.actions.SmiteAction;
import dragonmod.cards.Draconic.AbstractDraconicCard;
import dragonmod.powers.Dragonkin.ConfessionPower;
import dragonmod.powers.Dragonkin.Scorchpower;
import dragonmod.util.Wiz;

public class consecration extends AbstractDraconicCard {
    public static final String ID = consecration.class.getSimpleName();
    public consecration(){
        super(ID,1, AbstractCard.CardType.ATTACK,CardRarity.SPECIAL,CardTarget.ALL_ENEMY);
        setDamage(8,4);
        setMagic(3,1);
        isMultiDamage = true;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < 2; i++){
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters){
                Wiz.atb(new SmiteAction(mo,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL)));
                Wiz.applyToEnemy(mo,new Scorchpower(mo,p,magicNumber));
                Wiz.applyToSelf(new ConfessionPower(Wiz.Player(),magicNumber));
            }
        }
    }
}
