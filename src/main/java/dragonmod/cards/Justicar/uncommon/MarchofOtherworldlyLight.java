package dragonmod.cards.Justicar.uncommon;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DamageModifiers.HolyDamage;
import dragonmod.cards.Justicar.AbstractJusticarCard;

public class MarchofOtherworldlyLight extends AbstractJusticarCard {
    public static final String ID = MarchofOtherworldlyLight.class.getSimpleName();
    public MarchofOtherworldlyLight(){
        super(ID,2,CardType.ATTACK,CardRarity.UNCOMMON,CardTarget.ENEMY);
        setDamage(6,2);
        setMagic(4);
        DamageModifierManager.addModifier(this,new HolyDamage(true));
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }
}
