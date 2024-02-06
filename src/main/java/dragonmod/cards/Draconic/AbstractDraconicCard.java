package dragonmod.cards.Draconic;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.cards.AbstractDragonCard;

import java.util.ArrayList;

public abstract class AbstractDraconicCard extends AbstractDragonCard implements SpawnModificationCard {
    public AbstractDraconicCard(final String id,
                                final int cost,
                                final AbstractCard.CardType type,
                                final AbstractCard.CardRarity rarity,
                                final AbstractCard.CardTarget target) {

        super(id, cost, type, DragonMod.Draconic, rarity, target);

    }
    public AbstractDraconicCard(final String id,
                                final int cost,
                                final AbstractCard.CardType type,
                                final AbstractCard.CardColor color,
                                final AbstractCard.CardRarity rarity,
                                final AbstractCard.CardTarget target) {

        super(id, cost, type, color, rarity, target);

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }
    public boolean canSpawn(ArrayList<AbstractCard> currentRewardCards) {return false;}

    public boolean canSpawnShop(ArrayList<AbstractCard> currentShopCards) {return DragonMod.isPlayerDragon();}
}
