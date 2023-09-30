package dragonmod.cards.Rimedancer.Special;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.orbs.Icicle;
import dragonmod.util.CantripManager;
import dragonmod.util.Wiz;

public class BladedSleeves extends AbstractRimedancerCard {
        public static final String ID = BladedSleeves.class.getSimpleName();
        public BladedSleeves(){
            super(ID,0,CardType.SKILL,CardRarity.SPECIAL,CardTarget.SELF,true);
            setMagic(1,1);
            cardsToPreview = new Shiv();
            tags.add(CantripManager.Cantrip);
        }
        @Override
        public void use(AbstractPlayer p, AbstractMonster m) {
            Wiz.atb(new ChannelAction(new Icicle()));
            Wiz.atb(new MakeTempCardInHandAction(cardsToPreview.makeStatEquivalentCopy()));
        }
}
