package dragonmod.cards.Justicar;


import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.blockmods.BlockModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.AddIconToDescriptionMod;
import dragonmod.DamageModifiers.BlockModifiers.FireBlock;
import dragonmod.DamageModifiers.Icons.FireIcon;
import dragonmod.DragonMod;
import dragonmod.util.TriggerOnCycleEffect;

;

public class HeatBarrier extends AbstractPrimalCard implements TriggerOnCycleEffect {
    private static int realBlock = 8;
    public static final String ID = DragonMod.makeID(HeatBarrier.class.getSimpleName());
    public HeatBarrier() {
        super(ID, 1,CardType.SKILL,CardRarity.COMMON,CardTarget.SELF);
        setBlock(8,2);
        setMagic(4,2);
        BlockModifierManager.addModifier(this,new FireBlock(true));
        CardModifierManager.addModifier(this,new AddIconToDescriptionMod(AddIconToDescriptionMod.BLOCK, FireIcon.get()));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new GainBlockAction(p,block));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                baseBlock = realBlock;
                isDone = true;
            }
        });
        super.use(p,m);
    }
    @Override
    public void triggerWhenDrawn(){
        if (DragonMod.StatusesCycledThisTurn < 1){
            baseBlock = realBlock;
        }

    }
    @Override
    public void upgrade() {
        if (!upgraded) {
            realBlock += 2;
        }
        super.upgrade();
    }
    @Override
    public void TriggerOnCycle(AbstractCard ca) {
        baseBlock += magicNumber;
    }
}