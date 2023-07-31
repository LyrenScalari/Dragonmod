package dragonmod.cards.Justicar;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.evacipated.cardcrawl.mod.stslib.blockmods.BlockModifierManager;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.AddIconToDescriptionMod;
import dragonmod.DamageModifiers.BlockModifiers.FireBlock;
import dragonmod.DamageModifiers.Icons.FireIcon;
import dragonmod.DragonMod;
import dragonmod.actions.CycleAction;


public class Sunburst extends AbstractHolyCard {


    public static final String ID = DragonMod.makeID(Sunburst.class.getSimpleName());
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    private static final int COST = 1;
    private static final int BLOCK = 6;
    private static final int UPGRADE_PLUS_BLOCK = 4;
    private static final int MAGIC = 3;
    private static final int UPGRADE_MAGIC = 1;

    // /STAT DECLARATION/


    public Sunburst() {
        super(ID,COST, TYPE, RARITY, TARGET);
        damage = baseDamage = 6;
        SecondMagicNumber = BaseSecondMagicNumber = 1;
        block = baseBlock = BLOCK;
        cardsToPreview = new DivineEmber();
        BlockModifierManager.addModifier(this,new FireBlock(true));
        CardModifierManager.addModifier(this,new AddIconToDescriptionMod(AddIconToDescriptionMod.BLOCK, FireIcon.get()));
    }

    // Actions the card should do.

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m,new DamageInfo(m,damage, DamageInfo.DamageType.NORMAL)));
        addToBot(new SelectCardsInHandAction(SecondMagicNumber,Manipstrings.EXTENDED_DESCRIPTION[1],false,false,(card)->true,(List)-> {
            boolean blessed = false;
            for (AbstractCard c : List){
                if ((c instanceof AbstractPrimalCard || c.type == AbstractCard.CardType.STATUS || c.type == AbstractCard.CardType.CURSE) && !(c instanceof DivineEmber)){
                    addToBot(new CycleAction(c,0,new DivineEmber(c.makeSameInstanceOf())));
                } else {
                    addToBot(new CycleAction(c,0));
                    if (c instanceof AbstractHolyCard){
                        if (!blessed) {
                            addToBot(new GainBlockAction(p, block));
                            blessed = true;
                        }
                    }
                }

            }
        }));
        super.use(p,m);
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(2);
            upgradeBlock(2);
            initializeDescription();
        }
    }
}
