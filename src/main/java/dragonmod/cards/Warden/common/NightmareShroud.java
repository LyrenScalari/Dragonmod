package dragonmod.cards.Warden.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import dragonmod.actions.ExploitAction;
import dragonmod.cards.Warden.AbstractWardenCard;
import dragonmod.powers.Warden.HexPower;
import dragonmod.util.Wiz;

public class NightmareShroud extends AbstractWardenCard {
    public static final String ID = NightmareShroud.class.getSimpleName();
    public NightmareShroud(){
        super(ID,2,CardType.ATTACK,CardRarity.COMMON,CardTarget.ENEMY);
        setDamage(12,3);
        setMagic(3);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL));
        Wiz.atb(new DrawCardAction(1));
        Wiz.att(new AbstractGameAction() {
            @Override
            public void update() {
                if (ExploitAction.Exploited){
                    Wiz.applyToEnemy(m,new HexPower(m,p,magicNumber));
                }
                isDone = true;
            }
        });
        Wiz.att(new ExploitAction(()->new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL));
            }
        }, PoisonPower.POWER_ID,m));
    }
}