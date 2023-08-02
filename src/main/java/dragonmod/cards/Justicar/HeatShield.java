package dragonmod.cards.Justicar;


import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.actions.CycleAction;
import dragonmod.powers.Dragonkin.Scorchpower;


public class HeatShield extends AbstractPrimalCard {

    public static final String ID = HeatShield.class.getSimpleName();
    public HeatShield() {
        super(ID,1,CardType.SKILL,CardRarity.UNCOMMON,CardTarget.ENEMY);
        setBlock(7,3);
        setMagic(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        returnToHand = false;
        AbstractCard card = this;
        addToBot(new GainBlockAction(p,block));
        addToBot(new ApplyPowerAction(m,p,new Scorchpower(m,p,magicNumber)));
        addToBot(new SelectCardsInHandAction(1,Manipstrings.EXTENDED_DESCRIPTION[2],false,false,(cards)->true,(List)->{
            addToBot(new CycleAction(List.get(0),0));
            if (List.get(0).type == AbstractCard.CardType.STATUS){
                card.returnToHand = true;
            }}));
        super.use(p,m);
    }
}