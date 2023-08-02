package dragonmod.cards.Justicar;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.blockmods.BlockModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.AddIconToDescriptionMod;
import dragonmod.DamageModifiers.BlockModifiers.FireBlock;
import dragonmod.DamageModifiers.Icons.FireIcon;
import dragonmod.DragonMod;
import dragonmod.util.TriggerOnCycleEffect;


public class MoltenEmber extends AbstractJusticarCard implements TriggerOnCycleEffect {

    public static final String ID = MoltenEmber.class.getSimpleName();


    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.SPECIAL;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.STATUS;
    private static int realBlock = 8;
    private static final int COST = 0;
    private static final int UPGRADED_COST = 1;

    private static final int POTENCY = 8;
    private static final int UPGRADE_PLUS_DMG = 1;
    private static final int MAGIC = 4;
    private static final int UPGRADE_MAGIC = 2;

    public MoltenEmber() {
        super(ID,  COST, TYPE,  RARITY, TARGET);
        block = baseBlock = POTENCY;
        baseMagicNumber = magicNumber = MAGIC;
        SecondMagicNumber = BaseSecondMagicNumber = 2;
        exhaust = true;
        BlockModifierManager.addModifier(this,new FireBlock(true));
        CardModifierManager.addModifier(this,new AddIconToDescriptionMod(AddIconToDescriptionMod.BLOCK, FireIcon.get()));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p,block));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                baseBlock = realBlock;
                isDone = true;
            }
        });
    }

    @Override
    public void triggerWhenDrawn(){
        if (DragonMod.StatusesCycledThisTurn < 1){
            baseBlock = realBlock;
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(2);
            upgradeMagicNumber(UPGRADE_MAGIC);
            initializeDescription();
        }
    }

    @Override
    public void TriggerOnCycle(AbstractCard ca) {
        baseBlock += magicNumber;
    }
}