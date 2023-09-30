package dragonmod.cards.Justicar.uncommon;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.powers.general.ReinforcePower;
import dragonmod.util.Wiz;

public class ShoulderCheck extends AbstractJusticarCard {
    public static final String ID = ShoulderCheck.class.getSimpleName();
    public ShoulderCheck(){
        super(ID,2,CardType.ATTACK,CardRarity.UNCOMMON,CardTarget.ENEMY);
        setDamage(10,2);
    }
    public void triggerOnGlowCheck() {
        if (AbstractDungeon.player.hasPower(ReinforcePower.POWER_ID)) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL));
        if (AbstractDungeon.player.hasPower(ReinforcePower.POWER_ID)) {
            Wiz.atb(new DrawCardAction(1));
            Wiz.atb(new GainEnergyAction(2));
        }
    }
}