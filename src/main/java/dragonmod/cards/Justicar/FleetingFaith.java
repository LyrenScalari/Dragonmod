package dragonmod.cards.Justicar;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.SCVExaltCardmod;
import dragonmod.DragonMod;
import dragonmod.actions.ExaltAction;
import dragonmod.powers.Dragonkin.DivineConvictionpower;
import dragonmod.powers.Dragonkin.ReflectiveScales;
import dragonmod.util.TypeEnergyHelper;

public class FleetingFaith extends AbstractHolyCard {

    public static final String ID = DragonMod.makeID(FleetingFaith.class.getSimpleName());
    public FleetingFaith() {
        super(ID,1,CardType.SKILL,CardRarity.COMMON,CardTarget.SELF);

        energyCosts.put(TypeEnergyHelper.Mana.Exalt,SecondMagicNumber);
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
        addToBot(new GainBlockAction(p,block));
        addToBot(new ExaltAction(energyCosts.get(TypeEnergyHelper.Mana.Exalt),energyCosts,()-> new AbstractGameAction(){
            @Override
            public void update() {
                addToBot(new ApplyPowerAction(p,p,new ReflectiveScales(p,p,magicNumber)));
                isDone = true;
            }
        }));

        super.use(p,m);
    }

}