package dragonmod.cards.Justicar;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;

public class TailSlam extends AbstractPrimalCard {


    public static final String ID = TailSlam.class.getSimpleName();
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ALL;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    private static final int COST = 2;
    private static final int BLOCK = 20;
    private static final int UPGRADE_PLUS_BLOCK = 3;
    private static final int MAGIC = 5;
    public TailSlam() {
        super(ID, COST, TYPE, RARITY, TARGET);
        damage = baseDamage = 10;
        block = baseBlock = BLOCK;
        magicNumber = baseMagicNumber = MAGIC;
        isMultiDamage = true;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new WhirlwindEffect(Color.RED.cpy(),false)));
        addToBot(new DamageAction(p,new DamageInfo(p,magicNumber, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        addToBot(new GainBlockAction(p,block));
        addToBot(new VFXAction(new WhirlwindEffect(Color.RED.cpy(),true)));
        addToBot(new DamageAllEnemiesAction(p,damage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        super.use(p,m);
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            upgradeDamage(1);
            initializeDescription();
        }
    }
}