package dragonmod.cards.Justicar.starter;

import com.evacipated.cardcrawl.mod.stslib.blockmods.BlockModifierManager;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dragonmod.DamageModifiers.BlockModifiers.DivineBlock;
import dragonmod.DragonMod;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.powers.Dragonkin.InspirationPower;
import dragonmod.util.Wiz;

public class Cauterize extends AbstractJusticarCard {
    public static final String ID = Cauterize.class.getSimpleName();
    public Cauterize(){
        super(ID,1,CardType.SKILL,CardRarity.BASIC,CardTarget.SELF);
        setBlock(7,2);
        BlockModifierManager.addModifier(this,new DivineBlock(true));
        setMagic(2);
    }
    public void applyPowers() {
        int realBaseBlock = baseBlock;
        AbstractPower Inspiration = Wiz.Player().getPower(InspirationPower.POWER_ID);
        if (Inspiration != null) {
            baseBlock += Inspiration.amount;
        }
        super.applyPowers();
        this.baseBlock = realBaseBlock;
        this.isBlockModified = this.block != this.baseBlock;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.block(p, block);
        AbstractCard neighbor = DragonMod.getLeftCard(this);
        if (neighbor != null) {
            Wiz.atb(new DiscardSpecificCardAction(neighbor));
        }
    }
}
