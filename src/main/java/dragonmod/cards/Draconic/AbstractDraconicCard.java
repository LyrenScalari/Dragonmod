package dragonmod.cards.Draconic;

import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.cards.AbstractDragonCard;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDraconicCard extends AbstractDragonCard implements SpawnModificationCard {
    public static final UIStrings DragonAffinityTooltip = CardCrawlGame.languagePack.getUIString("dragonmod:DragonAffinityCard");
    public static final UIStrings DragonAffinitySpecial = CardCrawlGame.languagePack.getUIString("dragonmod:DragonAffinitySpecial");
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
    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo> retVal = new ArrayList<>();
        if (rarity != CardRarity.SPECIAL && rarity != CardRarity.CURSE){
            retVal.add(new TooltipInfo(DragonAffinityTooltip.TEXT[0], DragonAffinityTooltip.TEXT[1]));
        }
        return retVal;
    }

    @Override
    public List<String> getCardDescriptors() {
        ArrayList<String> retval = new ArrayList<>();
        if (rarity != CardRarity.SPECIAL && rarity != CardRarity.CURSE){
            retval.add(DragonAffinityTooltip.TEXT[0]);
        }
        return retval;
    }
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }
    public boolean canSpawn(ArrayList<AbstractCard> currentRewardCards) {return false;}

    public boolean canSpawnShop(ArrayList<AbstractCard> currentShopCards) {return DragonMod.isPlayerDragon() && (rarity != CardRarity.SPECIAL && rarity != CardRarity.CURSE);}
}
