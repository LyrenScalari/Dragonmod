package dragonmod.cards.Justicar;

import basemod.BaseMod;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsCenteredAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class ShadowVision extends AbstractPrimalCard {

    public static final String ID = ShadowVision.class.getSimpleName();
    private CardGroup ShadowVisons = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.NONE;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;

    private static final int COST = 0;
    private static final int UPGRADED_COST = 1;

    private static final int UPGRADE_PLUS_POTENCY = 0;
    private static final int MAGIC = 5;
    private static final int UPGRADE_MAGIC = 0;
    public ShadowVision() {
        super(ID, COST, TYPE,RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC;
        SecondMagicNumber = BaseSecondMagicNumber = 2;
        block = baseBlock = 8;
        cardsToPreview = new Burn();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if (AbstractDungeon.player.drawPile.size() > SecondMagicNumber){
                    ArrayList<AbstractCard> Visions = new ArrayList<>();
                    for (int i = 0; i < magicNumber; i++){
                        if (i < AbstractDungeon.player.drawPile.size()) {
                            Visions.add(AbstractDungeon.player.drawPile.getNCardFromTop(i));
                        } else {
                            break;
                        }
                    }
                    addToBot(new SelectCardsCenteredAction(Visions,SecondMagicNumber,Manipstrings.EXTENDED_DESCRIPTION[3]+ SecondMagicNumber + Manipstrings.EXTENDED_DESCRIPTION[4],true,(card)->true,abstractCards ->{
                        for (AbstractCard c : abstractCards) {
                            Visions.remove(c);
                            AbstractDungeon.player.drawPile.removeCard(c);
                            if (AbstractDungeon.player.hand.size() < BaseMod.MAX_HAND_SIZE){
                                AbstractDungeon.player.hand.addToTop(c);
                            } else addToTop(new MakeTempCardInDiscardAction(cardsToPreview.makeStatEquivalentCopy(),1));
                        }
                        for (AbstractCard Vision : Visions){
                            AbstractDungeon.player.drawPile.removeCard(Vision);
                            addToTop(new MakeTempCardInDiscardAction(cardsToPreview.makeStatEquivalentCopy(),1));
                        }
                        Visions.clear();
                    }));
                    addToBot(new AbstractGameAction() {
                        @Override
                        public void update() {
                            if (!Visions.isEmpty()){
                                for (AbstractCard Vision : Visions){
                                    AbstractDungeon.player.drawPile.removeCard(Vision);
                                    addToTop(new MakeTempCardInDiscardAction(cardsToPreview.makeStatEquivalentCopy(),1));
                                }
                            }
                            isDone = true;
                        }
                    });
                }
                isDone = true;
            }
        });
        super.use(p,m);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber2(1);
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}