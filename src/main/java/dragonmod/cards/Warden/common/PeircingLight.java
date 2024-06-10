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
import dragonmod.cards.Warden.AbstractWardenCard;
import dragonmod.powers.Warden.EmeraldBlossomPower;
import dragonmod.stances.DawnStance;
import dragonmod.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public class PeircingLight extends AbstractWardenCard {
    public static final String ID = PeircingLight.class.getSimpleName();
    public PeircingLight(){
        super(ID,1,CardType.ATTACK,CardRarity.COMMON,CardTarget.ENEMY);
        setDamage(7,3);
        setMagic(1,1);
    }
    @Override
    public List<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> retVal = new ArrayList<>();
        retVal.add(new TooltipInfo(AmethystBlossomString.TEXT[0],AmethystBlossomString.TEXT[1]));
        return retVal;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL));
        if (!p.stance.ID.equals(NeutralStance.STANCE_ID)){
            Wiz.applyToSelf(new EmeraldBlossomPower(magicNumber));
            Wiz.atb(new NotStanceCheckAction(NeutralStance.STANCE_ID, new VFXAction(new EmptyStanceEffect(p.hb.cX, p.hb.cY), 0.1F)));
            Wiz.atb(new ChangeStanceAction(new NeutralStance()));
            Wiz.atb(new DrawCardAction(1));
        } else {
            this.addToBot(new ChangeStanceAction(new DawnStance()));
        }
    }
}