package dragonmod.cards.Justicar;


import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.GainCustomBlockAction;
import com.evacipated.cardcrawl.mod.stslib.blockmods.BlockModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.AddIconToDescriptionMod;
import dragonmod.DamageModifiers.BlockModifiers.DivineBlock;
import dragonmod.DamageModifiers.Icons.LightIcon;
import dragonmod.DragonMod;
import dragonmod.util.TriggerOnCycleEffect;


public class GoldenFlames extends AbstractJusticarCard implements TriggerOnCycleEffect {

    public static final String ID = DragonMod.makeID(GoldenFlames.class.getSimpleName());

    public GoldenFlames() {
        super(ID,2,CardType.ATTACK,CardRarity.UNCOMMON,CardTarget.ENEMY);
        setDamage(6,2);
        setBlock(6,2);
        setMagic(2);
        BlockModifierManager.addModifier(this,new DivineBlock(true));
        CardModifierManager.addModifier(this,new AddIconToDescriptionMod(AddIconToDescriptionMod.BLOCK, LightIcon.get()));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        addToBot(new GainCustomBlockAction(this,p,block));
    }

    @Override
    public void TriggerOnCycle(AbstractCard ca) {
        baseDamage += magicNumber;
        damage += magicNumber;
        baseBlock += magicNumber;
        block += magicNumber;
    }
}