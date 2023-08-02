package dragonmod.cards.Justicar;

import com.evacipated.cardcrawl.mod.stslib.blockmods.BlockModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DamageModifiers.BlockModifiers.FireBlock;
import dragonmod.powers.Dragonkin.MoltenScalesPower;

public class MagmaScales extends AbstractPrimalCard {


    public static final String ID = MagmaScales.class.getSimpleName();

    // /TEXT DECLARATION/


    // STAT DECLARATION 	

    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.POWER;

    private static final int COST = 2;
    private static final int MAGIC = 1;
    private static final int UPGRADE_MAGIC = 1;

    // Hey want a second magic/damage/block/unique number??? Great!
    // Go check out DefaultAttackWithVariable and theDefault.variable.DefaultCustomVariable
    // that's how you get your own custom variable that you can use for anything you like.
    // Feel free to explore other mods to see what variables they personally have and create your own ones.

    // /STAT DECLARATION/


    public MagmaScales() {
        super(ID, COST, TYPE, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;
        BlockModifierManager.addModifier(this,new FireBlock(true));
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new MoltenScalesPower(p, p,magicNumber),magicNumber, false, AbstractGameAction.AttackEffect.FIRE));
        super.use(p,m);
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_MAGIC);
            initializeDescription();
        }
    }
}