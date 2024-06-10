package dragonmod.cards.Rimedancer.Uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.FlickCoinEffect;
import dragonmod.actions.ExploitAction;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.Wiz;

import java.util.ArrayList;

public class LeadAstray extends AbstractRimedancerCard {

    public static final String ID = LeadAstray.class.getSimpleName();
    public LeadAstray(){
        super(ID,1,CardType.SKILL,CardRarity.UNCOMMON,CardTarget.ENEMY);
        setMagic(2);
        setBlock(7);
        setMagic2(2,2);
    }
    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.vfx(new FlickCoinEffect(p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY));
        Wiz.applyToEnemy(m,new WeakPower(m,magicNumber,false));
        Wiz.block(p,block);
        ArrayList<AbstractCard> leftcards = new ArrayList<>();
        for (AbstractCard c : Wiz.Player().hand.group){
            if (Wiz.Player().hand.group.indexOf(c) < Wiz.Player().hand.group.indexOf(this)){
                leftcards.add(c);
            }
        }
        Wiz.att(new ExploitAction(()->new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (leftcards.size() <= magicNumber){
                    for (AbstractCard c : leftcards){
                        Wiz.Player().hand.removeCard(c);
                        EnchantmentsManager.addCard(c,true,Wiz.Player());
                    }
                } else {
                    Wiz.Player().hand.removeCard(leftcards.get(leftcards.size()-1));
                    leftcards.get(leftcards.size()-1).tags.add(EnchantmentsManager.Sleeved);
                    EnchantmentsManager.addCard(leftcards.get(leftcards.size()-1),true,Wiz.Player());
                    Wiz.Player().hand.removeCard(leftcards.get(leftcards.size()-2));
                    leftcards.get(leftcards.size()-2).tags.add(EnchantmentsManager.Sleeved);
                    EnchantmentsManager.addCard(leftcards.get(leftcards.size()-2),true,Wiz.Player());
                }
            }
        },2,m));
    }
}