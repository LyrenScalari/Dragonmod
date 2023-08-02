package dragonmod.cards.Justicar;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class FlameClaw extends AbstractPrimalCard {

    public static final String ID = FlameClaw.class.getSimpleName();
    public FlameClaw() {
        super(ID,1,CardType.ATTACK,CardRarity.COMMON,CardTarget.ENEMY);
        setDamage(12,5);
        setMagic(1);
        cardsToPreview = new Burn();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m,new DamageInfo(p,damage,DamageInfo.DamageType.NORMAL)));
        AbstractDungeon.actionManager.addToBottom(
                new MakeTempCardInDrawPileAction(new Burn(),magicNumber,true,false));
        super.use(p,m);
    }
}