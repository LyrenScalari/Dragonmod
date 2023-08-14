package dragonmod.cards.Rimedancer.Common;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.FrozenMod;
import dragonmod.CardMods.UnplayableCardMod;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.util.Wiz;

public class FreezeOver extends AbstractRimedancerCard {

    public static final String ID = FreezeOver.class.getSimpleName();

    public FreezeOver() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        setMagic(2, 1);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DrawCardAction(magicNumber, new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                for (AbstractCard c : DrawCardAction.drawnCards){
                    CardModifierManager.addModifier(c,new FrozenMod());
                    CardModifierManager.addModifier(c,new UnplayableCardMod());
                }
            }
        }));
    }
}
