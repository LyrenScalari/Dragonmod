package dragonmod.cards.Warden.starter.amethyst;

import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.NotStanceCheckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;
import dragonmod.DragonMod;
import dragonmod.cards.Warden.AbstractReflexiveCard;
import dragonmod.cards.Warden.starter.ShroudedSky;
import dragonmod.cards.Warden.starter.amber.AmberHaze;
import dragonmod.util.Wiz;
@NoCompendium
public class AmethystHaze extends AbstractReflexiveCard {

    public static final String ID = AmethystHaze.class.getSimpleName();
    public AmethystHaze(){
        super(ID,1,CardType.SKILL,CardRarity.BASIC,CardTarget.SELF);
        setMagic(2,1);
        setBlock(8);
        setBackgroundTexture(DragonMod.WARDEN_AMETHYST_ATTACK,DragonMod.WARDEN_AMETHYST_ATTACK_P);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.block(p,block);
        Wiz.atb(new DrawCardAction(magicNumber));
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