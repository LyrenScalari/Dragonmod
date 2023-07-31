package dragonmod.cards.Warden;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import dragonmod.DragonMod;
import dragonmod.actions.OnBeingScriedInterface;
import dragonmod.actions.RewindAction;
import dragonmod.util.Wiz;


public class BlastfromthePast extends AbstractWardenCard implements OnBeingScriedInterface {


    // TEXT DECLARATION

    public static final String ID = DragonMod.makeID(BlastfromthePast.class.getSimpleName());
   private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    private static final int COST = 1;
    private static final int BLOCK = 9;
    private static final int UPGRADE_PLUS_BLOCK = 3;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // /STAT DECLARATION/


    public BlastfromthePast() {
        super(ID, COST, TYPE, RARITY, TARGET);
        damage = baseDamage = 7;
        magicNumber = baseMagicNumber = 1;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(2);
            initializeDescription();
        }
    }

    @Override
    public void onBeingScried() {
        addToBot(new DiscardToHandAction(this));
        Wiz.applyToSelf(new DrawCardNextTurnPower(AbstractDungeon.player,1));
        addToTop(new SelectCardsInHandAction(magicNumber,Manipstrings.EXTENDED_DESCRIPTION[5],false,false,(card)->true,(List)-> {
            for (AbstractCard c : List){
                addToBot(new RewindAction(c,AbstractDungeon.player.hand));
            }
        }));
    }
}