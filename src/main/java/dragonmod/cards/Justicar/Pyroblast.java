package dragonmod.cards.Justicar;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.FireBurstParticleEffect;
import com.megacrit.cardcrawl.vfx.GhostlyFireEffect;
import com.megacrit.cardcrawl.vfx.combat.FireballEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import dragonmod.DragonMod;
import dragonmod.util.TriggerOnCycleEffect;


public class Pyroblast extends AbstractPrimalCard implements TriggerOnCycleEffect {

    public static final String ID = DragonMod.makeID(Pyroblast.class.getSimpleName());
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 6;
    private static final int UPGRADED_COST = 1;

    private static final int POTENCY = 10;
    private static final int UPGRADE_PLUS_DMG = 0;
    private static final int MAGIC = 1;
    private static final int UPGRADE_MAGIC = 1;
    public static int currentReduction = 0;

    public Pyroblast() {
        super(ID ,COST, TYPE, RARITY, TARGET);
        damage = baseDamage = POTENCY;
        baseMagicNumber = magicNumber = 10;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new InflameEffect(p)));
        addToBot(new VFXAction(new FireballEffect(AbstractDungeon.player.hb.cX,AbstractDungeon.player.hb.cY,m.hb.cX,m.hb.cY)));
        addToBot(new VFXAction(new InflameEffect(m)));
        addToBot(new VFXAction(new GhostlyFireEffect(m.hb.cX,m.hb.cY)));
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        addToBot(new VFXAction(new FireBurstParticleEffect(m.hb.cX,m.hb.cY)));
        super.use(p,m);
    }
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            selfRetain = true;
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void TriggerOnCycle(AbstractCard ca) {
            this.updateCost(-1);
            baseDamage += magicNumber;
            damage += magicNumber;
    }
}