package dragonmod.cards.Warden.starter.amethyst;

import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.cards.Warden.AbstractReflexiveCard;
import dragonmod.util.Wiz;

import static dragonmod.DragonMod.Flash;
@NoCompendium
public class RisingCresent extends AbstractReflexiveCard {
    public static final String ID = RisingCresent.class.getSimpleName();
    public RisingCresent(){
        super(ID,1,CardType.ATTACK,CardRarity.SPECIAL,CardTarget.ENEMY);
        setDamage(12,4);
        setMagic(2,1);
        setBackgroundTexture(DragonMod.WARDEN_AMETHYST_ATTACK,DragonMod.WARDEN_AMETHYST_ATTACK_P);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL));
        if (Flash()){
            Wiz.atb(new DrawCardAction(magicNumber));
        }
    }
}
