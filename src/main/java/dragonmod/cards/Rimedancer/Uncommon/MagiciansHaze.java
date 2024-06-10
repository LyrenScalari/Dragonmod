package dragonmod.cards.Rimedancer.Uncommon;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.RemoveAllOrbsAction;
import com.megacrit.cardcrawl.actions.unique.CalculatedGambleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.orbs.Icicle;
import dragonmod.util.Wiz;

public class MagiciansHaze extends AbstractRimedancerCard {
    public static final String ID = MagiciansHaze.class.getSimpleName();
    public MagiciansHaze(){
        super(ID,1,CardType.SKILL,CardRarity.UNCOMMON,CardTarget.NONE);
        setMagic2(5,-1);
        setMagic(1);
    }
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        int tilt = 0;
        for (AbstractCard c : Wiz.Player().hand.group){
            if (Wiz.Player().hand.group.indexOf(c) > Wiz.Player().hand.group.indexOf(this)){
                tilt += 1;
            }
        }
        if (tilt >= SecondMagicNumber){
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int orbcount = magicNumber;
        for (AbstractOrb o : Wiz.Player().orbs){
            if (!(o instanceof EmptyOrbSlot)){
                orbcount++;
            }
        }
        Wiz.atb(new RemoveAllOrbsAction());
        for (int i = 0; i < orbcount; i++){
            Wiz.atb(new ChannelAction(new Icicle()));
        }
        int tilt = 0;
        for (AbstractCard c : Wiz.Player().hand.group){
            if (Wiz.Player().hand.group.indexOf(c) > Wiz.Player().hand.group.indexOf(this)){
                tilt += 1;
            }
        }
        if (tilt >= SecondMagicNumber){
            Wiz.atb(new CalculatedGambleAction(true));
        }
    }
}