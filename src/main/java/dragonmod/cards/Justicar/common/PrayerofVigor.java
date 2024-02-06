package dragonmod.cards.Justicar.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.orbs.Verses.Vigor;
import dragonmod.powers.Dragonkin.DivineConvictionpower;
import dragonmod.util.HymnManager;
import dragonmod.util.Wiz;

public class PrayerofVigor extends AbstractJusticarCard {

    public static final String ID = PrayerofVigor.class.getSimpleName();
    public PrayerofVigor(){
        super(ID,1,CardType.SKILL,CardRarity.COMMON,CardTarget.SELF);
        setMagic(3,-1);
        setCustomVar("H",10,2);
        tags.add(HymnManager.Verse);
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
                HymnManager.addVerse(new Vigor(customVar( "H"),magicNumber), PrayerofVigor.this);
                isDone = true;
            }
        });
    }
}
