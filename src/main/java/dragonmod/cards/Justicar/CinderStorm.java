package dragonmod.cards.Justicar;

import basemod.BaseMod;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;


public class CinderStorm extends AbstractPrimalCard {

    public static final String ID = DragonMod.makeID(CinderStorm.class.getSimpleName());

    public CinderStorm() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        setDamage(7,4);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SelectCardsInHandAction(BaseMod.MAX_HAND_SIZE,Manipstrings.EXTENDED_DESCRIPTION[0],true,true,(card)->true,(List)-> {
            for (AbstractCard c : List){
                addToBot(new DrawCardAction(1));
                addToBot(new DiscardSpecificCardAction(c));
                addToBot(new DamageRandomEnemyAction(new DamageInfo(p,damage), AbstractGameAction.AttackEffect.LIGHTNING));
            }
        }));
        super.use(p,m);
    }
}