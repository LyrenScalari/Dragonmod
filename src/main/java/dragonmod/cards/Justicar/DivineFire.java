package dragonmod.cards.Justicar;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.powers.Dragonkin.Scorchpower;

public class DivineFire extends AbstractHolyCard {

    public static final String ID = DivineFire.class.getSimpleName();
    public DivineFire() {
        super(ID, 1,CardType.ATTACK,CardRarity.COMMON,CardTarget.ENEMY);
        setDamage(8,2);
        setMagic(3,2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        addToBot(new ApplyPowerAction(m,p,new Scorchpower(m,p,magicNumber)));
        super.use(p,m);
    }

}