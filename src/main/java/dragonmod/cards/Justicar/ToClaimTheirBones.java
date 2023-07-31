package dragonmod.cards.Justicar;

import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import dragonmod.DragonMod;
import dragonmod.util.Wiz;


public class ToClaimTheirBones extends AbstractHolyCard {

    public static final String ID = DragonMod.makeID(ToClaimTheirBones.class.getSimpleName());
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.SPECIAL;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    private static final int DAMAGE = 14;
    private static final int UPGRADE_PLUS_DMG = 2;
    private static final int MAGIC = 6;
    private static final int UPGRADE_MAGIC = 0;
    public ToClaimTheirBones() {
        super(ID, COST, TYPE, RARITY, TARGET,true);
        baseMagicNumber = magicNumber = 3;
        damage = baseDamage = 11;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        m.takeTurn();
        Wiz.applyToEnemy(m,new StunMonsterPower(m,1));
        Wiz.dmg(m,new DamageInfo(p,baseDamage));
        Wiz.dmg(m,new DamageInfo(p,baseDamage));
        Wiz.applyToEnemy(m,new VulnerablePower(m,magicNumber,false));
        super.use(p,m);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(5);
            selfRetain = true;
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}