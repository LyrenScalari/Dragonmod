package dragonmod.cards.Justicar;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PersistFields;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.SCVExaltCardmod;
import dragonmod.DragonMod;
import dragonmod.actions.ExaltAction;
import dragonmod.util.TypeEnergyHelper;


public class LightSpeedBlessing extends AbstractHolyCard {

    public static final String ID = DragonMod.makeID(LightSpeedBlessing.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private CardGroup ShadowVisons = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.NONE;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;

    private static final int COST = 0;
    private static final int UPGRADED_COST = 1;

    private static final int UPGRADE_PLUS_POTENCY = 0;
    private static final int MAGIC = 1;
    private static final int UPGRADE_MAGIC = 0;
    public LightSpeedBlessing() {
        super(ID,COST, TYPE,RARITY, TARGET);
        baseMagicNumber = magicNumber = 1;
        SecondMagicNumber = BaseSecondMagicNumber = 1;
        energyCosts.put(TypeEnergyHelper.Mana.Exalt,magicNumber);
        PersistFields.setBaseValue(this,3);
        CardModifierManager.addModifier(this,new SCVExaltCardmod());
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ExaltAction(energyCosts.get(TypeEnergyHelper.Mana.Exalt),energyCosts,()-> new AbstractGameAction(){
            @Override
            public void update() {
                addToBot(new DrawCardAction(magicNumber));
                isDone = true;
            }
        }));

        super.use(p,m);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            PersistFields.upgrade(this,2);
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}