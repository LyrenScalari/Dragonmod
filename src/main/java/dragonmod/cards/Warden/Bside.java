package dragonmod.cards.Warden;

import basemod.cardmods.EtherealMod;
import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsCenteredAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.actions.RewindAction;
import dragonmod.util.Wiz;

import java.util.ArrayList;

public class Bside extends AbstractWardenCard {


    // TEXT DECLARATION

    public static final String ID = Bside.class.getSimpleName(); // USE THIS ONE FOR THE TEMPLATE;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;  //   since they don't change much.
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;       //

    private static final int COST = 1;  // COST = 1
    private static final int UPGRADED_COST = 1; // UPGRADED_COST = 1

    private static final int DAMAGE = 8;    // DAMAGE = 6
    private static final int UPGRADE_PLUS_DMG = 1;  // UPGRADE_PLUS_DMG = 4
    // /STAT DECLARATION/

    public Bside(){
        super(ID, COST, TYPE, RARITY, TARGET);
        magicNumber = baseMagicNumber = 3;
    }
    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
            ArrayList<AbstractCard> canMove = new ArrayList<>(AbstractDungeon.player.discardPile.group);
            ArrayList<AbstractCard> selection = new ArrayList<>();
            for (int i = 1; i <= magicNumber; i++){
                if (canMove.isEmpty())
                    break;
                    selection.add(canMove.remove(AbstractDungeon.cardRng.random(canMove.size() - 1)));
            }
            addToBot(new SelectCardsCenteredAction(selection,1,cardStrings.EXTENDED_DESCRIPTION[0],false,card -> true,cards -> {
                Wiz.atb(new RewindAction(cards.get(0)));
                AbstractCard copy = cards.get(0).makeStatEquivalentCopy();
                CardModifierManager.addModifier(copy,new EtherealMod());
                CardModifierManager.addModifier(copy,new ExhaustMod());
                Wiz.atb(new MakeTempCardInHandAction(copy));
            }));

        }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
            initializeDescription();
        }
    }
}