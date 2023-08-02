package dragonmod.cards.Justicar;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.actions.CycleAction;
import dragonmod.orbs.WrathSeal;

public class Liebreaker extends AbstractHolyCard {

    public static final String ID = Liebreaker.class.getSimpleName();

    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ALL_ENEMY;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    private static final int POTENCY= 10;
    private static final int UPGRADE_PLUS_DMG = 1;
    private static final int MAGIC = 2;
    private static final int UPGRADE_MAGIC = 1;

    public Liebreaker() {
        super(ID, COST, TYPE, RARITY, TARGET);
        damage = baseDamage = POTENCY;
        block = baseBlock = POTENCY;
        heal = baseHeal = POTENCY;
        baseMagicNumber = magicNumber = MAGIC;
        SecondMagicNumber =BaseSecondMagicNumber = 7;
        cardsToPreview = new DivineEmber();
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SelectCardsInHandAction(magicNumber,Manipstrings.EXTENDED_DESCRIPTION[1],false,false,(card)->true,(List)-> {
            addToBot(new DamageAction(p,new DamageInfo(p,4, DamageInfo.DamageType.THORNS)));
            for (AbstractCard c : List){
                if ((c instanceof AbstractPrimalCard || c.type == AbstractCard.CardType.STATUS || c.type == AbstractCard.CardType.CURSE) && !(c instanceof DivineEmber)){
                    addToBot(new CycleAction(c,0,new DivineEmber(c.makeStatEquivalentCopy())));
                } else {
                    addToBot(new CycleAction(c,0));
                    if (c instanceof AbstractHolyCard){
                        addToBot(new AbstractGameAction() {
                            @Override
                            public void update() {
                                DragonMod.Seals.add(new WrathSeal(damage,SecondMagicNumber));
                                isDone = true;
                            }
                        });
                    }
                }
            }
        }));

        super.use(p,m);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }
}