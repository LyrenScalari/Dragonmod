package dragonmod.cards.Justicar;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsCenteredAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static dragonmod.characters.TheJusticar.Enums.Justicar_Red_COLOR;

public class DivinePrayer extends AbstractHolyCard {

    public static final String ID = DivinePrayer.class.getSimpleName();
    public static CardGroup Holy = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
    static {
        Holy.group = (ArrayList<AbstractCard>) CardLibrary.getAllCards()
                .stream()
                .filter(c -> c.color == Justicar_Red_COLOR)
                .filter(c -> c instanceof AbstractHolyCard)
                .filter(c -> !c.hasTag(AbstractCard.CardTags.HEALING))
                .filter(c -> !c.rarity.equals(AbstractCard.CardRarity.BASIC))
                .collect(Collectors.toList());
    }
    public CardGroup FilteredGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

    public DivinePrayer() {
        super(ID,1,CardType.SKILL,CardRarity.UNCOMMON,CardTarget.SELF);
        tags.add(AbstractCard.CardTags.HEALING);
        setCostUpgrade(0);
        setMagic(3,1);
        setExhaust(true);
        Holy.group = (ArrayList<AbstractCard>) CardLibrary.getAllCards()
                .stream()
                .filter(c -> c.color == Justicar_Red_COLOR)
                .filter(c -> c instanceof AbstractHolyCard)
                .filter(c -> !c.hasTag(AbstractCard.CardTags.HEALING))
                .filter(c -> !c.rarity.equals(AbstractCard.CardRarity.BASIC))
                .collect(Collectors.toList());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Holy.size() >= 1) {
            ArrayList<AbstractCard> cards = new ArrayList<>(Holy.group);
            int amt = Math.min(cards.size(), magicNumber);
            while (FilteredGroup.size() < amt) {
                FilteredGroup.addToTop(cards.remove(AbstractDungeon.miscRng.random(0, cards.size() - 1)));
            }
            addToBot(new SelectCardsCenteredAction(FilteredGroup.group, 1, cardStrings.EXTENDED_DESCRIPTION[0], List -> {
                AbstractCard cardtoget = List.get(0).makeCopy();
                AbstractDungeon.player.hand.addToHand(cardtoget);
                cardtoget.freeToPlayOnce = true;
                FilteredGroup.clear();
            }));
        }
        super.use(p,m);
    }
}