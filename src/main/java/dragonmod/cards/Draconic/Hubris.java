package dragonmod.cards.Draconic;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import dragonmod.DragonMod;
import dragonmod.interfaces.ReciveDamageinHandCard;
import dragonmod.util.Wiz;

import java.util.ArrayList;

public class Hubris extends AbstractDraconicCard implements SpawnModificationCard, ReciveDamageinHandCard {
    public static final String ID = Hubris.class.getSimpleName();
    public Hubris(){
        super(ID,-2,CardType.CURSE,CardColor.CURSE,CardRarity.CURSE,CardTarget.SELF);
        setSelfRetain(true);
        setMagic(1);
    }
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }
    public boolean canSpawn(ArrayList<AbstractCard> currentRewardCards) {return DragonMod.isPlayerDragon();}

    public boolean canSpawnShop(ArrayList<AbstractCard> currentShopCards) {return false;}

    @Override
    public int onReciveDamage(int damage, DamageInfo.DamageType type) {
        if (damage > AbstractDungeon.player.currentBlock && type == DamageInfo.DamageType.NORMAL){
            System.out.println("Your Hubris has been punished");
            Wiz.applyToSelf(new WeakPower(Wiz.Player(),1, true));
            Wiz.applyToSelf(new FrailPower(Wiz.Player(),1,true));
            Wiz.atb(new DiscardSpecificCardAction(this,AbstractDungeon.player.hand));
        }
        return damage;
    }
}
