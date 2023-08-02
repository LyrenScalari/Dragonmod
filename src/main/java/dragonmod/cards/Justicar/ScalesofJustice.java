package dragonmod.cards.Justicar;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.util.Wiz;


public class ScalesofJustice extends AbstractPrimalCard {

    public static final String ID = ScalesofJustice.class.getSimpleName();
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    public int drawn;
    private static final int COST = 0;
    private static final int UPGRADED_COST = 1;

    private static final int POTENCY = 8;
    private static final int UPGRADE_PLUS_POTENCY = 0;
    private static final int UPGRADE_MAGIC = 0;

    public ScalesofJustice() {
        super(ID, COST, TYPE, RARITY, TARGET);
        damage = baseDamage = POTENCY;
        magicNumber = baseMagicNumber = 4;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        drawn = 0;
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE);
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                addToBot(new AbstractGameAction() {
                    @Override
                    public void update() {
                        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                            if (c.type == AbstractCard.CardType.STATUS && drawn < magicNumber) {
                                addToTop(new AbstractGameAction() {
                                    @Override
                                    public void update() {
                                        AbstractDungeon.player.discardPile.group.remove(c);
                                        AbstractDungeon.player.drawPile.addToTop(c);
                                        addToTop(new DrawCardAction(1));
                                        isDone = true;
                                    }
                                });
                                drawn += 1;
                            }
                        }
                        isDone = true;
                    }});
                addToBot(new AbstractGameAction() {
                    @Override
                    public void update() {
                        if (drawn < magicNumber){
                            for (AbstractCard c : AbstractDungeon.player.discardPile.group){
                                if (c.type == AbstractCard.CardType.STATUS && drawn < magicNumber) {
                                    addToTop(new AbstractGameAction() {
                                        @Override
                                        public void update() {
                                            AbstractDungeon.player.discardPile.group.remove(c);
                                            AbstractDungeon.player.drawPile.addToTop(c);
                                            addToTop(new DrawCardAction(1));
                                            isDone = true;
                                        }
                                    });
                                    drawn += 1;
                                }
                            }
                        }
                        isDone = true;
                    }
                });
                isDone = true;
            }});
        super.use(p,m);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(2);
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}

