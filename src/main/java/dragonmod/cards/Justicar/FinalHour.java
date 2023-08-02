package dragonmod.cards.Justicar;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.SCVExaltCardmod;
import dragonmod.actions.ExaltAction;
import dragonmod.powers.Dragonkin.DivineConvictionpower;
import dragonmod.powers.Dragonkin.DivineRetributionPower;
import dragonmod.powers.Dragonkin.PenancePower;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;


public class FinalHour extends AbstractHolyCard {

    public static final String ID = FinalHour.class.getSimpleName();
    public FinalHour() {
        super(ID, 1,CardType.SKILL,CardRarity.SPECIAL,CardTarget.ALL_ENEMY);
        setMagic(5,-1);
        setMagic2(24,8);
        setExhaust(true);
        energyCosts.put(TypeEnergyHelper.Mana.Exalt,magicNumber);
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
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                new AbstractGameAction(){
                    @Override
                    public void update() {
                        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters){
                            if (!m.isDeadOrEscaped()){
                                addToBot(new ApplyPowerAction(m,p,new PenancePower(m,p,SecondMagicNumber)));
                            }
                        }
                        isDone = true;
                    }
            };}});
        //TO-DO : Make the following the proper effect.
        addToBot(new ExaltAction(energyCosts.get(TypeEnergyHelper.Mana.Exalt),energyCosts,()-> new ApplyPowerAction(p,p,new DivineRetributionPower(p,p,0))));
        super.use(p,m);
    }
}