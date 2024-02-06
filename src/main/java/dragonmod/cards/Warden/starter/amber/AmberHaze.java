package dragonmod.cards.Warden.starter.amber;

import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.NotStanceCheckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;
import dragonmod.DragonMod;
import dragonmod.cards.Warden.AbstractReflexiveCard;
import dragonmod.cards.Warden.starter.ShroudedSky;
import dragonmod.cards.Warden.starter.amethyst.AmethystHaze;
import dragonmod.util.Wiz;
@NoCompendium
public class AmberHaze extends AbstractReflexiveCard {

    public static final String ID = AmberHaze.class.getSimpleName();
    public AmberHaze(){
        super(ID,2,CardType.ATTACK,CardRarity.BASIC,CardTarget.ENEMY);
        setDamage(8,2);
        setBlock(10,2);
        setBackgroundTexture(DragonMod.WARDEN_AMBER_ATTACK,DragonMod.WARDEN_AMBER_ATTACK_P);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.block(p,block);
        Wiz.dmg(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL));
        Wiz.dmg(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL));
        Wiz.atb(new NotStanceCheckAction("Neutral", new VFXAction(new EmptyStanceEffect(p.hb.cX, p.hb.cY), 0.1F)));
        Wiz.atb(new ChangeStanceAction("Neutral"));
    }
    public AbstractCard getAmberCard(){
        return new AmberHaze();
    }
    public AbstractCard getAmethystCard(){
        return new AmethystHaze();
    }
    public AbstractCard getEmeraldCard(){
        return new ShroudedSky();
    }
}
