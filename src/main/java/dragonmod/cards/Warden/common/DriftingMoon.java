package dragonmod.cards.Warden.common;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.ConjuredCardMod;
import dragonmod.cards.Warden.AbstractWardenCard;
import dragonmod.util.Wiz;

import java.util.ArrayList;

public class DriftingMoon extends AbstractWardenCard {
    public static final String ID = DriftingMoon.class.getSimpleName();
    public DriftingMoon(){
        super(ID,1,CardType.SKILL,CardRarity.COMMON,CardTarget.ENEMY);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> exhaustpile = new ArrayList<>();
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
               exhaustpile.addAll(Wiz.Player().exhaustPile.group);
                ArrayList<AbstractCard> filtered = new ArrayList<>();
               for (AbstractCard c: exhaustpile){
                   if (!(c.type == CardType.STATUS || c.type == CardType.CURSE)){
                       filtered.add(c);
                   }
               }
               AbstractCard Conjure = filtered.get(AbstractDungeon.miscRng.random(0,filtered.size()-1));
               CardModifierManager.addModifier(Conjure,new ConjuredCardMod());
               Wiz.Player().exhaustPile.removeCard(Conjure);
               Wiz.att(new MakeTempCardInDrawPileAction(Conjure,1,true,false));
            }
        });
        if (upgraded){
            Wiz.atb(new DrawCardAction(1));
        }
    }
}
