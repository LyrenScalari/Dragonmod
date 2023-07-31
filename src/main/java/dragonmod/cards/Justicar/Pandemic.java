package dragonmod.cards.Justicar;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DragonMod;

public class Pandemic extends AbstractJusticarCard {

    public static final String ID = DragonMod.makeID(Pandemic.class.getSimpleName());



    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;

    private static final int POTENCY = 14;
    private static final int UPGRADE_PLUS_POTENCY = 0;
    private static final int MAGIC = 1;
    private static final int UPGRADE_MAGIC = 0;

    public Pandemic() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractPower po : m.powers){
            if (po.type == AbstractPower.PowerType.DEBUFF) {
                    if (po instanceof CloneablePowerInterface) {
                        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                                AbstractPower temp = ((CloneablePowerInterface)po).makeCopy();
                                temp.owner = mo;
                                addToBot(new ApplyPowerAction(mo, p, temp));
                    }
                }
            }
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}

