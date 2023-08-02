package dragonmod.cards.Justicar;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ViolentAttackEffect;
import dragonmod.DragonMod;
import dragonmod.orbs.InfusionSeal;
import dragonmod.util.CustomTags;


public class VenegefulStrike extends AbstractHolyCard {

    public static final String ID = VenegefulStrike.class.getSimpleName();
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    private static final int UPGRADE_PLUS_POTENCY = 0;
    private static final int MAGIC = 3;
    private static final int UPGRADE_MAGIC = 0;
    public VenegefulStrike() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC;
        damage = baseDamage = 10;
        BaseSecondMagicNumber =SecondMagicNumber = 8;
        tags.add(CustomTags.Blessing);

    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new ViolentAttackEffect(m.drawX,m.drawY, Color.GOLD)));
        addToBot(new DamageAction(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SMASH));
        AbstractCard that = this;
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                DragonMod.Seals.add(new InfusionSeal(magicNumber,SecondMagicNumber,that));
                isDone = true;
            }
        });
        super.use(p,m);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(2);
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}