package dragonmod.cards.Justicar;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.actions.SmiteAction;
import dragonmod.util.CustomTags;


public class ChemicalStar extends AbstractPrimalCard {

    public static final String ID = ChemicalStar.class.getSimpleName();
    public ChemicalStar() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.SELF_AND_ENEMY);
        setDamage(15,3);
        setMagic(3);
        cardsToPreview = new Burn();
        tags.add(CustomTags.Smite);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(p,new DamageInfo(p,magicNumber, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
        addToBot(new SmiteAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
        addToBot(new MakeTempCardInDiscardAction(new Burn(),1));
    }
}