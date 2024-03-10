package dragonmod.cards.Warden.rare.amethyst;

import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.NotStanceCheckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.NeutralStance;
import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;
import dragonmod.DragonMod;
import dragonmod.cards.Warden.AbstractReflexiveCard;
import dragonmod.powers.Warden.HexPower;
import dragonmod.util.Wiz;

@NoCompendium
@NoPools
public class RavenousViper extends AbstractReflexiveCard {

    public static final String ID = RavenousViper.class.getSimpleName();
    public RavenousViper(){
        super(ID,2, AbstractCard.CardType.ATTACK, CardRarity.RARE, AbstractCard.CardTarget.ENEMY);
        setDamage(12,4);
        setMagic(12,4);
        setBackgroundTexture(DragonMod.WARDEN_AMETHYST_ATTACK,DragonMod.WARDEN_AMETHYST_ATTACK_P);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL));
        Wiz.applyToEnemy(m,new HexPower(m,p,magicNumber));
        Wiz.atb(new NotStanceCheckAction(NeutralStance.STANCE_ID, new VFXAction(new EmptyStanceEffect(p.hb.cX, p.hb.cY), 0.1F)));
        Wiz.atb(new ChangeStanceAction(new NeutralStance()));
        Wiz.atb(new DrawCardAction(1));
    }
}
