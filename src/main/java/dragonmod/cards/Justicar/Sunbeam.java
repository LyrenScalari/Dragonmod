package dragonmod.cards.Justicar;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import dragonmod.DragonMod;

public class Sunbeam extends AbstractHolyCard {


    public static final String ID = DragonMod.makeID(Sunbeam.class.getSimpleName());


    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;

    private static final int COST = 1;
    private static final int BLOCK = 7;
    private static final int UPGRADE_PLUS_BLOCK = 4;
    private static final int MAGIC = 2;
    private static final int UPGRADE_MAGIC = 1;


    // /STAT DECLARATION/

    public Sunbeam() {
        super(ID, COST, TYPE, RARITY, TARGET);
        damage = baseDamage = BLOCK;
        magicNumber = baseMagicNumber = MAGIC;
        SecondMagicNumber = BaseSecondMagicNumber = 3;
    }

    // Actions the card should do.

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
        addToBot(new ApplyPowerAction(m,p,new WeakPower(m,magicNumber,false)));
        addToBot(new DamageAction(p,new DamageInfo(p,SecondMagicNumber, DamageInfo.DamageType.THORNS)));
        super.use(p,m);
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(2);
            upgradeMagicNumber(UPGRADE_MAGIC);
            upgradeMagicNumber2(1);
        }
    }
}