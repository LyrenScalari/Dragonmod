package dragonmod.cards.Rimedancer.Uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.actions.IcicleRandomDamageAction;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.util.Wiz;

import java.util.ArrayList;

public class PokerDart extends AbstractRimedancerCard {

    public static final String ID = PokerDart.class.getSimpleName();
    public PokerDart(){
        super(ID,2,CardType.ATTACK,CardRarity.UNCOMMON,CardTarget.ALL_ENEMY);
        setCostUpgrade(1);
        setDamage(3);
        setBlock(3);
    }
    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> leftcards = new ArrayList<>();
        ArrayList<AbstractCard> rightcards = new ArrayList<>();
        for (AbstractCard c : Wiz.Player().hand.group){
            if (Wiz.Player().hand.group.indexOf(c) < Wiz.Player().hand.group.indexOf(this)){
                leftcards.add(c);
            }
            if (Wiz.Player().hand.group.indexOf(c) > Wiz.Player().hand.group.indexOf(this)){
                rightcards.add(c);
            }
        }
        Wiz.atb(new IcicleRandomDamageAction(new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        for (int i = 0; i < leftcards.size(); i++){
            Wiz.atb(new IcicleRandomDamageAction(new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
        Wiz.block(p, block + (block* rightcards.size()));
    }
}
