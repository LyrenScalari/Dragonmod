package dragonmod.cards.Justicar;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.actions.SmiteAction;
import dragonmod.orbs.SparkSeal;
import dragonmod.powers.Dragonkin.Scorchpower;
import dragonmod.util.CustomTags;


public class PartingShot extends AbstractPrimalCard {

    public static final String ID = PartingShot.class.getSimpleName();


    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;

    private static final int COST = 2;
    private static final int BLOCK = 18;
    private static final int UPGRADE_PLUS_BLOCK = 4;
    private static final int MAGIC = 1;


    // /STAT DECLARATION/


    public PartingShot() {
        super(ID, COST, TYPE,  RARITY, TARGET);
        damage = baseDamage = 8;
        SecondMagicNumber = BaseSecondMagicNumber = 10;
        magicNumber = baseMagicNumber = 4;
        tags.add(CustomTags.Smite);
    }

    // Actions the card should do.

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new SmiteAction(m, new DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.NORMAL)));
        addToBot(new ApplyPowerAction(m,p,new Scorchpower(m,p,magicNumber)));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                DragonMod.Seals.add(new SparkSeal(damage,SecondMagicNumber));
                isDone = true;
            }
        });
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