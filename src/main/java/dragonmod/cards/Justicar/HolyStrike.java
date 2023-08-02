package dragonmod.cards.Justicar;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HolyStrike extends AbstractHolyCard {

    public static final String ID = HolyStrike.class.getSimpleName(); // USE THIS ONE FOR THE TEMPLATE;
    public HolyStrike(){
        super(ID,1,CardType.ATTACK,CardRarity.BASIC,CardTarget.ENEMY);
        setDamage(5,2);
        this.tags.add(AbstractCard.CardTags.STARTER_STRIKE);
        this.tags.add(AbstractCard.CardTags.STRIKE);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        super.use(p,m);
    }
}