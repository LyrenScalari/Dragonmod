package dragonmod.cards.Rimedancer.Common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.orbs.Icicle;
import dragonmod.util.Wiz;

public class WisteriaCutter extends AbstractRimedancerCard {
    public static final String ID = WisteriaCutter.class.getSimpleName();
    public WisteriaCutter(){
        super(ID,2,CardType.ATTACK,CardRarity.COMMON,CardTarget.ENEMY);
        setDamage(12,3);
        setMagic(7,-1);
    }
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        int handcount = 0;
        for (AbstractCard c : Wiz.Player().hand.group){
            handcount++;
        }
        if (handcount >= magicNumber){
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        for (int i = 0; i < magicNumber; i++){
            Wiz.atb(new ChannelAction(new Icicle()));
        }
        int handcount = 0;
        for (AbstractCard c : Wiz.Player().hand.group){
            handcount++;
        }
        int finalHandcount = handcount;
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (finalHandcount > (magicNumber-1)){
                    Wiz.att(new GainEnergyAction(2));
                }
            }
        });
    }
}
