package dragonmod.cards.Justicar.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.orbs.Verses.Beacon;
import dragonmod.powers.Dragonkin.DivineConvictionpower;
import dragonmod.util.HymnManager;
import dragonmod.util.Wiz;

public class BeaconofLight extends AbstractJusticarCard {

    public static final String ID = BeaconofLight.class.getSimpleName();
    public BeaconofLight(){
        super(ID,1,CardType.SKILL,CardRarity.UNCOMMON,CardTarget.SELF);
        setMagic(2);
        setCustomVar("H",4,1);
        setVarCalculation("H", (m, base) -> {
            AbstractPower p = null;
            if (AbstractDungeon.player != null){
                p = AbstractDungeon.player.getPower(DivineConvictionpower.POWER_ID);
            }
            if(p != null){
                return base + p.amount;
            } else return base;
        });
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                HymnManager.addVerse(new Beacon(block,magicNumber),BeaconofLight.this);
                HymnManager.addVerse(new Beacon(block,magicNumber),BeaconofLight.this);
                isDone = true;
            }
        });
    }
}
