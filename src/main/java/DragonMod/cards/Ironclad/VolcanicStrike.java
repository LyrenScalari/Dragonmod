package DragonMod.cards.Ironclad;

import DragonMod.actions.ApplyCardModifierAction;
import DragonMod.cardmods.ExhaustMod;
import DragonMod.cards.AbstractEasyCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static DragonMod.DragonMod.makeID;
import static DragonMod.DragonMod.modID;
import static DragonMod.util.Wiz.atb;

public class VolcanicStrike extends AbstractEasyCard {
    public final static String ID = makeID("VolcanicStrike");
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(modID+":UIText");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(modID+":"+ID);
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public VolcanicStrike() {
        super(ID, 1, AbstractCard.CardType.ATTACK, CardRarity.COMMON, AbstractCard.CardTarget.ENEMY, CardColor.RED);
        baseDamage = 8;
        tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        atb(new SelectCardsInHandAction(1,uiStrings.TEXT[0],false,false,card -> card.type == CardType.ATTACK, List -> {
            AbstractCard cardtomake = List.get(0).makeStatEquivalentCopy();
            atb(new ApplyCardModifierAction(cardtomake, new ExhaustMod()));
            if (!upgraded) {
                atb(new MakeTempCardInHandAction(cardtomake));
            } else atb(new MakeTempCardInHandAction(cardtomake,2));
        }));
    }

    public void upp() {
        upgradeDamage(3);
    }
}
