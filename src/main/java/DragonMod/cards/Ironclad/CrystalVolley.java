package DragonMod.cards.Ironclad;

import DragonMod.DragonMod;
import DragonMod.actions.ApplyCardModifierAction;
import DragonMod.cardmods.ExhaustMod;
import DragonMod.cards.AbstractEasyCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static DragonMod.DragonMod.makeID;
import static DragonMod.util.Wiz.atb;

public class CrystalVolley extends AbstractEasyCard {
    public final static String ID = makeID("CrystalVolley");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public CrystalVolley() {
        super(ID, 1, AbstractCard.CardType.ATTACK, CardRarity.COMMON, AbstractCard.CardTarget.ENEMY, CardColor.RED);
        baseDamage = 7;
        baseMagicNumber = magicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        if (DragonMod.wasDamagedThisTurn){
            AbstractCard copy = this.makeStatEquivalentCopy();
            atb(new ApplyCardModifierAction(copy, new ExhaustMod()));
            atb(new MakeTempCardInHandAction(copy));
        }
    }

    public void upp() {
        upgradeDamage(3);
    }
}