package dragonmod.cards.Warden.special;

import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Warden.AbstractWardenCard;
import dragonmod.stances.DawnStance;

@NoCompendium
public class DawnStanceCard extends AbstractWardenCard {

    public static final String ID = DawnStanceCard.class.getSimpleName();
    public DawnStanceCard(){
        super(ID,-2,CardType.SKILL,CardRarity.SPECIAL,CardTarget.SELF);
    }
    public void onChoseThisOption() {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new ChangeStanceAction(new DawnStance()));
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.onChoseThisOption();
    }
}