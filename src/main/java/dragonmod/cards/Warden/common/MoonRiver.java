package dragonmod.cards.Warden.common;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.NotStanceCheckAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.NeutralStance;
import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;
import dragonmod.cards.Draconic.BanishingVerse;
import dragonmod.cards.Warden.AbstractWardenCard;
import dragonmod.powers.Warden.BanishPower;
import dragonmod.stances.DuskStance;
import dragonmod.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public class MoonRiver extends AbstractWardenCard {
    public static final String ID = MoonRiver.class.getSimpleName();
    public MoonRiver(){
        super(ID,1,CardType.SKILL,CardRarity.COMMON,CardTarget.SELF);
        setBlock(4,2);
        setMagic(2,1);
        cardsToPreview = new BanishingVerse();
    }
    @Override
    public List<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> retVal = new ArrayList<>();
        retVal.add(new TooltipInfo(AmberBlossomString.TEXT[0],AmberBlossomString.TEXT[1]));
        return retVal;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL));
        Wiz.applyToSelf(new BanishPower(p,p,magicNumber));
        if (!p.stance.ID.equals(NeutralStance.STANCE_ID)){
            Wiz.atb(new NotStanceCheckAction(NeutralStance.STANCE_ID, new VFXAction(new EmptyStanceEffect(p.hb.cX, p.hb.cY), 0.1F)));
            Wiz.atb(new ChangeStanceAction(new NeutralStance()));
            Wiz.atb(new DrawCardAction(1));
        } else {
            this.addToBot(new ChangeStanceAction(new DuskStance()));
        }
    }
}
