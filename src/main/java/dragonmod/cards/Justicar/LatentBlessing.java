package dragonmod.cards.Justicar;


import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.GainCustomBlockAction;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.evacipated.cardcrawl.mod.stslib.blockmods.BlockModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import dragonmod.CardMods.AddIconToDescriptionMod;
import dragonmod.DamageModifiers.BlockModifiers.DivineBlock;
import dragonmod.DamageModifiers.Icons.LightIcon;
import dragonmod.DragonMod;
import dragonmod.actions.CycleAction;
import dragonmod.util.Wiz;


public class LatentBlessing extends AbstractHolyCard {

    public static final String ID = DragonMod.makeID(LatentBlessing.class.getSimpleName());


    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 0;
    private static final int UPGRADED_COST = 1;

    private static final int POTENCY= 7;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static final int MAGIC = 8;
    private static final int UPGRADE_MAGIC = 2;

    public LatentBlessing() {
        super(ID,COST, TYPE, RARITY, TARGET);
        block = baseBlock = 8;
        SecondMagicNumber =BaseSecondMagicNumber = 2;
        BlockModifierManager.addModifier(this,new DivineBlock(true));
        CardModifierManager.addModifier(this,new AddIconToDescriptionMod(AddIconToDescriptionMod.BLOCK, LightIcon.get()));
        cardsToPreview = new DivineEmber();
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new DrawCardNextTurnPower(p,1));
        if (!AbstractDungeon.player.hand.isEmpty()) {
            if (AbstractDungeon.player.hand.size() <= 2) {
                if (AbstractDungeon.player.hand.getTopCard() instanceof AbstractPrimalCard || AbstractDungeon.player.hand.getTopCard().type == AbstractCard.CardType.STATUS || AbstractDungeon.player.hand.getTopCard().type == AbstractCard.CardType.CURSE) {
                    addToBot(new CycleAction(AbstractDungeon.player.hand.getTopCard(), 0, new DivineEmber(AbstractDungeon.player.hand.getTopCard().makeStatEquivalentCopy())));
                } else {
                    addToBot(new CycleAction(AbstractDungeon.player.hand.getTopCard(), 0));
                    if (AbstractDungeon.player.hand.getTopCard() instanceof AbstractHolyCard) {
                        addToBot(new GainCustomBlockAction(this, p, block));
                    }
                }
            } else {
                addToBot(new SelectCardsInHandAction(SecondMagicNumber, Manipstrings.EXTENDED_DESCRIPTION[1], true, false, (cards) -> true, (List) -> {
                    boolean blessed = false;
                    for (AbstractCard c : List) {
                        if ((c instanceof AbstractPrimalCard || c.type == AbstractCard.CardType.STATUS || c.type == AbstractCard.CardType.CURSE) && !(c instanceof DivineEmber)) {
                            addToBot(new CycleAction(c, 0, new DivineEmber(c.makeStatEquivalentCopy())));
                        } else {
                            addToBot(new CycleAction(c, 0));
                            if (c instanceof AbstractHolyCard && !blessed) {
                                blessed = true;
                                addToBot(new GainCustomBlockAction(this, p, block));
                            }
                        }
                    }
                }));
            }
        }
        super.use(p,m);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(3);
            initializeDescription();
        }
    }
}