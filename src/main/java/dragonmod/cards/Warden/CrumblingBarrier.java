package dragonmod.cards.Warden;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.SCVTemporalCardMod;
import dragonmod.DragonMod;
import dragonmod.actions.BacklashAction;
import dragonmod.patches.TemporalStressField;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;


public class CrumblingBarrier extends AbstractWardenCard {


    // TEXT DECLARATION

    public static final String ID = DragonMod.makeID(CrumblingBarrier.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.BASIC; //  Up to you, I like auto-complete on these
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;  //   since they don't change much.
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;       //
    private static final int COST = 1;  // COST = 1
    private static final int UPGRADED_COST = 1; // UPGRADED_COST = 1

    private static final int DAMAGE = 6;    // DAMAGE = 6
    private static final int UPGRADE_PLUS_DMG = 3;  // UPGRADE_PLUS_DMG = 4

    // /STAT DECLARATION/
    public CrumblingBarrier(){
        super(ID, COST, TYPE, RARITY, TARGET);
        energyCosts.put(TypeEnergyHelper.Mana.Temporal,8);
        block = baseBlock = 8;
        magicNumber = baseMagicNumber = 2;
        CardModifierManager.addModifier(this,new SCVTemporalCardMod());
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (TemporalStressField.Stress.get(AbstractDungeon.player) >= energyCosts.get(TypeEnergyHelper.Mana.Temporal)) {
            if (upgraded){
                Wiz.atb(new BacklashAction(energyCosts, () -> new AbstractGameAction() {
                    @Override
                    public void update() {
                        Wiz.att(new DrawCardAction(1));
                        Wiz.att(new HealAction(p,p,magicNumber));
                    }
                }));
            } else Wiz.atb(new BacklashAction(energyCosts, () -> new DrawCardAction(1)));
        } else {
            Wiz.block(p,block);
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(3);
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}