package dragonmod.cards.Warden.starter;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Warden.AbstractReflexiveCard;
import dragonmod.cards.Warden.starter.amber.SupernatrualWind;
import dragonmod.cards.Warden.starter.amethyst.RisingCresent;
import dragonmod.util.Wiz;
import dragonmod.util.BlossomManager;
public class WillowSplitter extends AbstractReflexiveCard {
    public static final String ID = WillowSplitter.class.getSimpleName();
    public WillowSplitter(){
        super(ID,1,CardType.ATTACK,CardRarity.BASIC,CardTarget.ENEMY);
        setDamage(4,2);
        setReflectivePairing(new SupernatrualWind(), new RisingCresent());
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL));
        Wiz.dmg(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL));
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                BlossomManager.addEmeraldBlossom(1);
            }
        });
    }
}

