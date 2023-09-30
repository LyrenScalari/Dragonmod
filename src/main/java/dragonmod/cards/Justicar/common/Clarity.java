package dragonmod.cards.Justicar.common;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.actions.ExaltAction;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.powers.Dragonkin.DivineConvictionpower;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;

public class Clarity extends AbstractJusticarCard {

    public static final String ID = Clarity.class.getSimpleName();
    public Clarity(){
        super(ID,1,CardType.SKILL,CardRarity.COMMON,CardTarget.SELF);
        setCostUpgrade(0);
        setMagic(2);
        energyCosts.put(TypeEnergyHelper.Mana.Exalt,2);
    }
    public void triggerOnGlowCheck() {
        if (AbstractDungeon.player.hasPower(DivineConvictionpower.POWER_ID)) {
            if (AbstractDungeon.player.getPower(DivineConvictionpower.POWER_ID).amount >= energyCosts.get(TypeEnergyHelper.Mana.Exalt)){
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
            } else {
                this.glowColor = Color.RED.cpy();
            }
        } else {
            this.glowColor = Color.RED.cpy();
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new ExaltAction(energyCosts,()->new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                Wiz.atb(new DrawCardAction(magicNumber));
                Wiz.atb(new GainEnergyAction(2));
            }
        }));
    }
}
