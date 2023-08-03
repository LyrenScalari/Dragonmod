package dragonmod.cards.Rimedancer.Special;

import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.orbs.Icicle;
import dragonmod.util.Wiz;

public class PilferedCrystal extends AbstractRimedancerCard {
    public static final String ID = PilferedCrystal.class.getSimpleName();
    public PilferedCrystal(){
        super(ID,1,CardType.ATTACK,CardRarity.BASIC,CardTarget.ENEMY,true);
        setDamage(10,2);
        setMagic(8,4);
        setMagic2(2,3);
        setExhaust(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m, new DamageInfo(p, baseDamage, DamageInfo.DamageType.NORMAL));
        boolean Fenced = false;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if (c instanceof PilferedCrystal) {
                Fenced = true;
                break;
            }
        }
        if (Fenced) {
            for (int i = 0; i < SecondMagicNumber; i++){
                Wiz.atb(new ChannelAction(new Icicle()));
            }
        } else {
            Wiz.atb(new GainGoldAction(magicNumber));
        }
    }
}