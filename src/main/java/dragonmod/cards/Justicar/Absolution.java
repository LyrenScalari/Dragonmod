package dragonmod.cards.Justicar;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.actions.common.GainCustomBlockAction;
import com.evacipated.cardcrawl.mod.stslib.blockmods.BlockModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.AddIconToDescriptionMod;
import dragonmod.CardMods.SCVExaltCardmod;
import dragonmod.DamageModifiers.BlockModifiers.DivineBlock;
import dragonmod.DamageModifiers.Icons.LightIcon;
import dragonmod.actions.ExaltAction;
import dragonmod.powers.Dragonkin.DivineConvictionpower;
import dragonmod.powers.Dragonkin.PenancePower;
import dragonmod.util.TypeEnergyHelper;


public class Absolution extends AbstractHolyCard {

    public static final String ID = Absolution.class.getSimpleName();
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;
    private static final int UPGRADE_PLUS_POTENCY = 0;
    private static final int MAGIC = 8;
    private static final int UPGRADE_MAGIC = 0;
    public Absolution() {
        super(ID,COST,TYPE,RARITY,TARGET);
        setMagic(8);
        setBlock(8,4);
        setMagic2(20,15);
        BlockModifierManager.addModifier(this,new DivineBlock(true));
        CardModifierManager.addModifier(this,new AddIconToDescriptionMod(AddIconToDescriptionMod.BLOCK, LightIcon.get()));
        energyCosts.put(TypeEnergyHelper.Mana.Exalt,4);
        CardModifierManager.addModifier(this,new SCVExaltCardmod());
    }
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (AbstractDungeon.player.hasPower(DivineConvictionpower.POWER_ID) && (AbstractDungeon.player.getPower(DivineConvictionpower.POWER_ID).amount >= energyCosts.get(TypeEnergyHelper.Mana.Exalt))) {
            this.glowColor = Color.GOLDENROD.cpy();
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m,p,new PenancePower(m,p,magicNumber)));
        addToBot(new GainCustomBlockAction(this,p,block));
        addToBot(new ExaltAction(energyCosts.get(TypeEnergyHelper.Mana.Exalt),energyCosts,()-> new AbstractGameAction() {
            @Override
            public void update() {
                PenancePower.Power += SecondMagicNumber;
                isDone = true;
            }
        }));
        super.use(p,m);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            energyCosts.put(TypeEnergyHelper.Mana.Exalt,3);
            super.upgrade();
        }
    }
}