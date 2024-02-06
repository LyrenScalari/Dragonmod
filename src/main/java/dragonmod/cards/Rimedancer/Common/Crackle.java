package dragonmod.cards.Rimedancer.Common;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.actions.FireAction;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.orbs.Icicle;
import dragonmod.util.Wiz;

public class Crackle extends AbstractRimedancerCard {

    public static final String ID = Crackle.class.getSimpleName();

    public Crackle() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        setMagic(1);
        setBlock(8,4);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new FireAction(Icicle.class));
        boolean frozen = false;
        for (AbstractCard c : p.hand.group){
            if (CardModifierManager.hasModifier(c,"FrozenMod")){
                frozen = true;
            }
        }
        if (frozen){
            Wiz.block(p,block);
        }
    }
}