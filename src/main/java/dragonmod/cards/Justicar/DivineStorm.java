package dragonmod.cards.Justicar;


import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import dragonmod.DragonMod;
import dragonmod.actions.SmiteAction;
import dragonmod.powers.Dragonkin.SacrificePower;

public class DivineStorm extends AbstractHolyCard {

    public static final String ID = DragonMod.makeID(DivineStorm.class.getSimpleName());
    public static int repeats = 0;
    // /STAT DECLARATION/


    public DivineStorm() {
        super(ID, -1,CardType.ATTACK,CardRarity.RARE,CardTarget.ALL_ENEMY);
        setDamage(9);
        setMagic(0,2);
        setExhaust(true);
        setSelfRetain(false,true);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (AbstractDungeon.player.hasRelic("Chemical X")) {
            repeats += 2;
            AbstractDungeon.player.getRelic("Chemical X").flash();
        }
        repeats += EnergyPanel.totalCount;
        addToBot(new TalkAction(true,cardStrings.EXTENDED_DESCRIPTION[0],1.25f,1.50f));
        for (int i = 0; i < magicNumber+repeats; ++i) {
            m = AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng);
            if (m != null) {
                addToBot(new SmiteAction(m, new DamageInfo(p, damage, damageTypeForTurn)));
            }
        }
        addToBot(new ApplyPowerAction(p,p,new SacrificePower(p,p,repeats)));
        EnergyPanel.useEnergy(EnergyPanel.totalCount);
        repeats = 0;
        super.use(p,m);
    }

}