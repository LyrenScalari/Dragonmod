package dragonmod.cards.Justicar.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.powers.Dragonkin.ConfessionPower;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.Wiz;

public class MarchofOtherworldlyLight extends AbstractJusticarCard {
    public static final String ID = MarchofOtherworldlyLight.class.getSimpleName();
    public MarchofOtherworldlyLight(){
        super(ID,2,CardType.ATTACK,CardRarity.UNCOMMON,CardTarget.ENEMY);
        setDamage(8,2);
        setMagic(4);
        setCustomVar("DevaD",8,2);
        setVarCalculation("DevaD", (m, base) -> {
            if (AbstractDungeon.player != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT){
                int tmp = this.baseDamage;
                this.baseDamage = base;
                if (m != null)
                    super.calculateCardDamage(m);
                else
                    super.applyPowers();
                this.baseDamage = tmp;
                return damage + EnchantmentsManager.getDevotion();
            } else {
                return base;
            }
        });
        setCustomVar("DevaM",4,0);
        setVarCalculation("DevaM", (m, base) -> {
            if (AbstractDungeon.player != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
                return magicNumber + EnchantmentsManager.getDevotion();
            } else {
                return base;
            }
        });
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m,new DamageInfo(p,customVar("DevaD"), DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE);
        Wiz.applyToSelf(new ConfessionPower(p,customVar("DevaM")));
    }
}
