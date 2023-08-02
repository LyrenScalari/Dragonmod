package dragonmod.cards.Warden;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import dragonmod.util.Wiz;


public class DissoantWhispers extends AbstractWardenCard {


    // TEXT DECLARATION

    public static final String ID = (DissoantWhispers.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;  //   since they don't change much.
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;       //
    private static final int COST = 2;  // COST = 1
    private static final int UPGRADED_COST = 1; // UPGRADED_COST = 1

    private static final int DAMAGE = 6;    // DAMAGE = 6
    private static final int UPGRADE_PLUS_DMG = 3;  // UPGRADE_PLUS_DMG = 4

    // /STAT DECLARATION/


    public DissoantWhispers(){
        super(ID, COST, TYPE, RARITY, TARGET);
        block = baseBlock = 12;
        magicNumber = baseMagicNumber = 2;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p,block));
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                Wiz.atb(new VFXAction(new PurgeCardEffect(AbstractDungeon.player.drawPile.getTopCard().makeStatEquivalentCopy())));
                if (AbstractDungeon.player.drawPile.getTopCard().type == AbstractCard.CardType.STATUS){
                        addToBot(new GainEnergyAction(magicNumber));
                        AbstractDungeon.player.exhaustPile.addToTop(AbstractDungeon.player.drawPile.getTopCard());
                        AbstractDungeon.player.drawPile.removeCard(AbstractDungeon.player.drawPile.getTopCard());
                } else {
                    AbstractDungeon.player.drawPile.removeCard(AbstractDungeon.player.drawPile.getTopCard());
                    AbstractDungeon.player.discardPile.addToTop(AbstractDungeon.player.drawPile.getTopCard());
                }
                isDone = true;
            }
        });
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(4);
            initializeDescription();
        }
    }
}
