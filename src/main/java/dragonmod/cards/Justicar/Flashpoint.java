package dragonmod.cards.Justicar;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.SCVExaltCardmod;
import dragonmod.actions.ExaltAction;
import dragonmod.powers.Dragonkin.DivineConvictionpower;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;

public class Flashpoint extends AbstractHolyCard {

    public static final String ID = Flashpoint.class.getSimpleName();

    public Flashpoint() {
        super(ID, 0,CardType.SKILL,CardRarity.UNCOMMON,CardTarget.SELF);
        setBlock(10,4);
        setMagic(1);
        setMagic2(2,1);
        energyCosts.put(TypeEnergyHelper.Mana.Exalt, magicNumber);
        CardModifierManager.addModifier(this, new SCVExaltCardmod());
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        } else if (!(p.hasPower(DivineConvictionpower.POWER_ID))) {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        } else if (p.getPower(DivineConvictionpower.POWER_ID).amount < energyCosts.get(TypeEnergyHelper.Mana.Exalt)) {
            return false;
        } else {
            return super.canUse(p, m);
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (AbstractDungeon.player.hasPower(DivineConvictionpower.POWER_ID) && (AbstractDungeon.player.getPower(DivineConvictionpower.POWER_ID).amount >= energyCosts.get(TypeEnergyHelper.Mana.Exalt))) {
            this.glowColor = Color.GOLDENROD.cpy();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ExaltAction(energyCosts.get(TypeEnergyHelper.Mana.Exalt), energyCosts, () -> new AbstractGameAction() {
            @Override
            public void update() {
                for (int i = 0; i < SecondMagicNumber; i++) {
                    addToBot(new GainEnergyAction(1));
                }
                Wiz.block(p, block);
                isDone = true;
            }
        }));
        super.use(p, m);
    }
}