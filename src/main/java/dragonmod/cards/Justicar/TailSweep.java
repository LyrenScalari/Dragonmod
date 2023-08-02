package dragonmod.cards.Justicar;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;

public class TailSweep extends AbstractPrimalCard {

    public static final String ID = TailSweep.class.getSimpleName();

    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ALL;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    private static final int COST = 2;
    private static final int BLOCK = 10;
    private static final int UPGRADE_PLUS_BLOCK = 4;
    private static final int MAGIC = 1;


    // /STAT DECLARATION/


    public TailSweep() {
        super(ID, COST, TYPE, RARITY, TARGET);
        block = baseBlock = BLOCK;
        magicNumber = baseMagicNumber = MAGIC;
        SecondMagicNumber = BaseSecondMagicNumber = 2;
    }

    // Actions the card should do.

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new WhirlwindEffect(Color.RED.cpy(),false)));
        addToBot(new GainBlockAction(p,block));
        for (AbstractMonster M : AbstractDungeon.getCurrRoom().monsters.monsters){
            addToBot(new ApplyPowerAction(M,p,new WeakPower(M,SecondMagicNumber,false)));
        }
        super.use(p,m);
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
}