package dragonmod.cards.Warden;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.actions.OnBeingScriedInterface;
import dragonmod.patches.FieldsField;
import dragonmod.util.FieldCard;
import dragonmod.util.Wiz;


public class TimeWalk extends AbstractWardenCard implements OnBeingScriedInterface {


    // TEXT DECLARATION

    public static final String ID = TimeWalk.class.getSimpleName();
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    private static final int COST = 2;
    private static final int BLOCK = 13;
    private static final int UPGRADE_PLUS_BLOCK = 3;

    // /STAT DECLARATION/
    public TimeWalk() {
        this(true);
    }

    public TimeWalk(boolean real) {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseBlock = BLOCK;
        if (real){
            cardsToPreview = new TimeWalk(false);
            cardsToPreview.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
            cardsToPreview.initializeDescription();
        }
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        if (Foreseen){
            if (!FieldsField.Fields.get(Wiz.adp()).isEmpty()) {
                for (AbstractCard card : FieldsField.Fields.get(Wiz.adp())) {
                    Wiz.atb(new AbstractGameAction() {
                        @Override
                        public void update() {
                            ((FieldCard) card).AttachedTurnStart(Wiz.adp());
                            this.isDone = true;
                        }
                    });
                }
            }
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!FieldsField.Fields.get(m).isEmpty()) { //78
                    for (AbstractCard card : FieldsField.Fields.get(m)) {
                        Wiz.atb(new AbstractGameAction() {
                            @Override
                            public void update() {
                                ((FieldCard) card).AttachedTurnStart(mo);
                                this.isDone = true;
                            }
                        });
                    }
                }
            }
            Wiz.atb(new AbstractGameAction() {
                @Override
                public void update() {
                    Foreseen = false;
                    rawDescription = cardStrings.DESCRIPTION;
                    cardsToPreview = new TimeWalk(false);
                    cardsToPreview.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
                    cardsToPreview.initializeDescription();
                    initializeDescription();
                    isDone = true;
                }
            });
        }
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            initializeDescription();
        }
    }

    @Override
    public void onBeingScried() {
        addToBot(new DiscardToHandAction(this));
        Foreseen = true;
        rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
        cardsToPreview = null;
        initializeDescription();
    }
}
