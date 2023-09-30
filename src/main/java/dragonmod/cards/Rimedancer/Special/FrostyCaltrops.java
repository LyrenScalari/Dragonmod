package dragonmod.cards.Rimedancer.Special;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlickCoinEffect;
import dragonmod.actions.GainCrystalOrbSlotAction;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.util.CantripManager;
import dragonmod.util.Wiz;


public class FrostyCaltrops extends AbstractRimedancerCard {
    public static final String ID = FrostyCaltrops.class.getSimpleName();
    public FrostyCaltrops(){
        super(ID,1,CardType.ATTACK,CardRarity.SPECIAL,CardTarget.ENEMY,true);
        setDamage(8,4);
        tags.add(CantripManager.Cantrip);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.vfx(new FlickCoinEffect(p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY));
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        Wiz.atb(new GainCrystalOrbSlotAction(1));
    }
}