package dragonmod.cards.Justicar.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.Wiz;

public class Dawnlight extends AbstractJusticarCard {
    public static final String ID = Dawnlight.class.getSimpleName();
    public Dawnlight(){
        super(ID,1,CardType.ATTACK,CardRarity.COMMON,CardTarget.ALL);
        setCustomVar("DevaD",2,1);
        setVarCalculation("DevaD", (m, base) -> {
            if (AbstractDungeon.player != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT){
                int tmp = this.baseDamage;
                this.baseDamage = base;
                if (m != null)
                    super.calculateCardDamage(m);
                else
                    super.applyPowers();
                this.baseDamage = tmp;
                return damage * EnchantmentsManager.getDevotion();
            } else {
                return base;
            }
        });
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DamageAllEnemiesAction(p,customVar("DevaD"), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.LIGHTNING));
    }
}
