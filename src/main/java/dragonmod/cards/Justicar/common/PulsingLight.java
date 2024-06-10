package dragonmod.cards.Justicar.common;

import com.evacipated.cardcrawl.mod.stslib.blockmods.BlockModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DamageModifiers.BlockModifiers.DivineBlock;
import dragonmod.actions.CureAction;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.powers.Dragonkin.ZealPower;
import dragonmod.util.StigmataManager;
import dragonmod.util.Wiz;

public class PulsingLight extends AbstractJusticarCard {

    public static final String ID = PulsingLight.class.getSimpleName();
    public PulsingLight(){
        super(ID,1,CardType.SKILL,CardRarity.COMMON,CardTarget.SELF);
        setBlock(4);
        setCustomVar("H",8,4);
        BlockModifierManager.addModifier(this, new DivineBlock(true));
        setVarCalculation("H", (m, base) -> {
            AbstractPower p = null;
            if (AbstractDungeon.player != null){
                p = AbstractDungeon.player.getPower(ZealPower.POWER_ID);
            }
            if(p != null){
                return base + p.amount;
            } else return base;
        });
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new CureAction(customVar("H")));
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (StigmataManager.Overheal > 0){
                    Wiz.atb(new GainBlockAction(Wiz.Player(),block+(StigmataManager.Overheal/2)));
                }
            }
        });
    }
}
