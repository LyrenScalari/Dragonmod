package dragonmod.cards.Justicar.common;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.actions.SmiteAction;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.util.Wiz;

public class LightsReach extends AbstractJusticarCard {
    public static final String ID = LightsReach.class.getSimpleName();
    public LightsReach(){
        super(ID,1,CardType.ATTACK,CardRarity.COMMON,CardTarget.ALL);
        setDamage(9,3);
        setBlock(5,2);
        isMultiDamage = true;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters){
            if (mo.getIntentBaseDmg() > 0){
                Wiz.atb(new SmiteAction(mo,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL)));
            } else {
                Wiz.atb(new GainBlockAction(mo,block));
            }
        }
        Wiz.atb(new GainBlockAction(p,block));
    }
}
