package dragonmod.cards.Warden;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.patches.FieldsManager;
import dragonmod.util.FieldCard;
import java.util.ArrayList;
import java.util.List;

public class TheWhimsofFate extends AbstractWardenCard implements FieldCard {
    public static final String ID = TheWhimsofFate.class.getSimpleName(); // USE THIS ONE FOR THE TEMPLATE;
    private static final UIStrings VentTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:Field");
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;  //   since they don't change much.
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.POWER;       //
    private static final int COST = 2;  // COST = 1
    private static final int UPGRADED_COST = 1; // UPGRADED_COST = 1

    private static final int DAMAGE = 6;    // DAMAGE = 6
    private static final int UPGRADE_PLUS_DMG = 3;  // UPGRADE_PLUS_DMG = 4

    // /STAT DECLARATION/
    public TheWhimsofFate(){
        super(ID, COST, TYPE, RARITY, TARGET);
        tags.add(DragonMod.Field);
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo> tips = new ArrayList<>();
        tips.add(new TooltipInfo(VentTooltip.TEXT[0],VentTooltip.TEXT[1]));
        return tips;
    }
    @Override
    public List<String> getCardDescriptors() {
        List<String> retVal = new ArrayList<>();
        retVal.add(VentTooltip.TEXT[0]);
        return retVal;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                FieldsManager.addCard(TheWhimsofFate.this,true,p);
                isDone = true;
            }
        });
    }

    public void AttachedTurnStart(AbstractCreature owner){

    }
    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}
