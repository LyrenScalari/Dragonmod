package dragonmod.cards.Warden;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.actions.RewindAction;

public class ConvectionMirage extends AbstractWardenCard {


    // TEXT DECLARATION

    public static final String ID = (ConvectionMirage.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;  //   since they don't change much.
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;       //
    private static final int COST = 2;  // COST = 1
    private static final int UPGRADED_COST = 1; // UPGRADED_COST = 1

    private static final int DAMAGE = 7;    // DAMAGE = 6
    private static final int UPGRADE_PLUS_DMG = 3;  // UPGRADE_PLUS_DMG = 4

    // /STAT DECLARATION/


    public ConvectionMirage(){
        super(ID, COST, TYPE, RARITY, TARGET);
        block = baseBlock = 11;
        magicNumber = baseMagicNumber = 1;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p,block));
        if (AbstractDungeon.player.hand.contains(this)){
            if (AbstractDungeon.player.hand.group.indexOf(this)-1 >= 0){
                addToBot(new RewindAction(AbstractDungeon.player.hand.group.get(AbstractDungeon.player.hand.group.indexOf(this)-1),AbstractDungeon.player.hand));
            }
            if (AbstractDungeon.player.hand.group.indexOf(this)+1 <= AbstractDungeon.player.hand.group.size()-1){
                addToBot(new RewindAction(AbstractDungeon.player.hand.group.get(AbstractDungeon.player.hand.group.indexOf(this)+1),AbstractDungeon.player.hand));
            }
        }
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            upgradeBlock(3);
            initializeDescription();
        }
    }
}