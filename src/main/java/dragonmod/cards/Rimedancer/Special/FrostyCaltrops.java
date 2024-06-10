package dragonmod.cards.Rimedancer.Special;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlickCoinEffect;
import dragonmod.actions.GainCrystalOrbSlotAction;
import dragonmod.cards.Draconic.AbstractDraconicCard;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.Wiz;


public class FrostyCaltrops extends AbstractDraconicCard {
    public static final String ID = FrostyCaltrops.class.getSimpleName();
    public FrostyCaltrops(){
        super(ID,1,CardType.ATTACK,CardRarity.SPECIAL,CardTarget.ENEMY);
        setDamage(8,4);
        tags.add(EnchantmentsManager.Cantrip);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.vfx(new FlickCoinEffect(p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY));
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        Wiz.atb(new GainCrystalOrbSlotAction(1));
    }
}