package dragonmod.cards.Justicar;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;

public class HolyDefend extends AbstractHolyCard {


    // TEXT DECLARATION

    public static final String ID = DragonMod.makeID(HolyDefend.class.getSimpleName());

    public HolyDefend() {
        super(ID, 1,CardType.SKILL,CardRarity.BASIC,CardTarget.SELF);
        setBlock(6,3);
        this.tags.add(AbstractCard.CardTags.STARTER_DEFEND); //Tag your strike, defend and form (Wraith form, Demon form, Echo form, etc.) cards so that they function correctly.
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        super.use(p,m);
    }
}

