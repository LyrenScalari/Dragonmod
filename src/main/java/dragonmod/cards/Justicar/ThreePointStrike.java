package dragonmod.cards.Justicar;


import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.actions.SmiteAction;
import dragonmod.util.CustomTags;
import dragonmod.util.TriggerOnCycleEffect;

import java.util.ArrayList;

public class ThreePointStrike extends AbstractHolyCard implements TriggerOnCycleEffect {


    public static final String ID = DragonMod.makeID(ThreePointStrike.class.getSimpleName());

    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;

    private static final int COST = 0;
    private static final int BLOCK = 18;
    private static final int UPGRADE_PLUS_BLOCK = 4;
    private static final int MAGIC = 3;
    // /STAT DECLARATION/
    private final ArrayList<AbstractDamageModifier> normalDamage = new ArrayList<>();

    public ThreePointStrike() {
        super(ID, COST, TYPE, RARITY, TARGET);
        ThirdDamage = baseThirdDamage = secondDamage = baseSecondDamage = damage = baseDamage = 8;
        magicNumber = baseMagicNumber = MAGIC;
        tags.add(CustomTags.Smite);
    }

    // Actions the card should do.

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SmiteAction(m, new DamageInfo(p, damage, damageTypeForTurn)));
        super.use(p,m);
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(3);
            upgradeDamage2(3);
            initializeDescription();
        }
    }

    @Override
    public void TriggerOnCycle(AbstractCard ca) {
        if (AbstractDungeon.player.discardPile.contains(this)){
            this.addToBot(new DiscardToHandAction(this));
        }
    }
}