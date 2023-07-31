package dragonmod.cards.Justicar;


import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.util.TriggerOnCycleEffect;

public class Cinderstrike extends AbstractPrimalCard implements TriggerOnCycleEffect {

    public static final String ID = DragonMod.makeID(Cinderstrike.class.getSimpleName());
    public Cinderstrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        setDamage(8,2);
        setMagic(2,1);
        tags.add(AbstractCard.CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
        super.use(p,m);
    }

    @Override
    public void TriggerOnCycle(AbstractCard ca) {
        baseDamage += magicNumber;
        damage += magicNumber;
    }
}