package dragonmod.cards.Warden;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import dragonmod.patches.FieldsManager;
import dragonmod.util.Wiz;


public class ShredderChord extends AbstractWardenCard {


    // TEXT DECLARATION

    public static final String ID = ShredderChord.class.getSimpleName(); // USE THIS ONE FOR THE TEMPLATE;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON; //  Up to you, I like auto-complete on these
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;  //   since they don't change much.
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;       //
    private static final int COST = 1;  // COST = 1
    private static final int UPGRADED_COST = 1; // UPGRADED_COST = 1

    private static final int DAMAGE = 8;    // DAMAGE = 6
    private static final int UPGRADE_PLUS_DMG = 4;  // UPGRADE_PLUS_DMG = 4
    // /STAT DECLARATION/

    public ShredderChord(){
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage =DAMAGE;
        magicNumber = baseMagicNumber = 3;
        cardsToPreview = new Trance();
    }
    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                Wiz.atb(new VFXAction(new PurgeCardEffect(AbstractDungeon.player.drawPile.getTopCard().makeStatEquivalentCopy())));
                if (AbstractDungeon.player.drawPile.getTopCard().type == AbstractCard.CardType.SKILL){
                    addToBot(new AbstractGameAction() {
                        @Override
                        public void update() {
                            AbstractCard Trance = cardsToPreview.makeStatEquivalentCopy();
                            Trance.applyPowers();
                            Trance.calculateCardDamage(m);
                            Trance.use(p,m);
                            FieldsManager.addCard(Trance,true,m);
                            isDone = true;
                        }
                    });
                    Wiz.atb(new DrawCardAction(1));
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
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}