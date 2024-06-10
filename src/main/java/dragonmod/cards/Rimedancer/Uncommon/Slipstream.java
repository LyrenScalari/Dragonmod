package dragonmod.cards.Rimedancer.Uncommon;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.RetainCardMod;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.powers.Rimedancer.Chillpower;
import dragonmod.util.Wiz;

public class Slipstream extends AbstractRimedancerCard {

    public static final String ID = Slipstream.class.getSimpleName();
    public Slipstream(){
        super(ID,0,CardType.SKILL,CardRarity.UNCOMMON,CardTarget.ENEMY);
        setBlock(4,2);
        setMagic(4,2);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.block(p,block);
        if (m != null && m.getIntentBaseDmg() >= 0){
            CardModifierManager.addModifier(Wiz.Player().hand.getBottomCard(),new RetainCardMod(1));
            Wiz.applyToEnemy(m,new Chillpower(m,p,magicNumber));
        }
    }
}
