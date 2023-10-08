package dragonmod.cards.Justicar.uncommon;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DamageModifiers.HolyDamage;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.util.HymnManager;

public class MarchofOtherworldlyLight extends AbstractJusticarCard {
    public static final String ID = MarchofOtherworldlyLight.class.getSimpleName();
    public MarchofOtherworldlyLight(){
        super(ID,2,CardType.ATTACK,CardRarity.UNCOMMON,CardTarget.ENEMY);
        setCustomVar("DevaD",8,2);
        setVarCalculation("DevaD", (m, base) -> {
            if (AbstractDungeon.player != null){
                int tmp = this.baseDamage;
                this.baseDamage = base;
                if (m != null)
                    super.calculateCardDamage(m);
                else
                    super.applyPowers();
                this.baseDamage = tmp;
                return damage + HymnManager.getDevotion();
            } else {
                return base;
            }
        });
        setCustomVar("Deva",0,0);
        setVarCalculation("Deva", (m, base) -> {
            if (AbstractDungeon.player != null){
                return HymnManager.getDevotion();
            } else {
                return base;
            }
        });
        setCustomVar("DevaM",0,0);
        setVarCalculation("DevaM", (m, base) -> {
            if (AbstractDungeon.player != null){
                return HymnManager.getDevotion() + magicNumber;
            } else {
                return base;
            }
        });
        setMagic(4);
        DamageModifierManager.addModifier(this,new HolyDamage(true));
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }
}
