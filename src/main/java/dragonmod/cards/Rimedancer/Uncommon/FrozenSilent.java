package dragonmod.cards.Rimedancer.Uncommon;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsCenteredAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.FrozenMod;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.util.Wiz;

import static basemod.helpers.CardModifierManager.hasModifier;

public class FrozenSilent extends AbstractRimedancerCard {
    public static final String ID = FrozenSilent.class.getSimpleName();

    public FrozenSilent() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        setBlock(7,3);
        setMagic(1,1);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.block(p,block);
        Wiz.atb(new SelectCardsCenteredAction(p.hand.group,magicNumber, cardStrings.EXTENDED_DESCRIPTION[0],false,(card)-> {
            return hasModifier(card, "FrozenMod");
        },(cards)->{
            for (AbstractCard card : cards){
                CardModifierManager.addModifier(card,new FrozenMod());
                card.freeToPlayOnce = true;
                p.hand.removeCard(card);
                p.drawPile.addToBottom(card);
            }
        }));
        boolean frozen = false;
        for (AbstractCard c : p.hand.group){
            if (hasModifier(c,"FrozenMod")){
                frozen = true;
            }
        }
        if (frozen){
            Wiz.atb(new DrawCardAction(1));
        }
    }
}
