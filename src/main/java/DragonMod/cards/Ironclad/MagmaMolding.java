package DragonMod.cards.Ironclad;

import DragonMod.actions.ApplyCardModifierAction;
import DragonMod.cardmods.ExhaustMod;
import DragonMod.cards.AbstractEasyCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static DragonMod.DragonMod.makeID;
import static DragonMod.DragonMod.modID;
import static DragonMod.util.Wiz.atb;

public class MagmaMolding extends AbstractEasyCard {
    public final static String ID = makeID("MagmaMolding");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(modID+":UIText");
    public MagmaMolding() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, CardColor.RED);
        baseMagicNumber = magicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SelectCardsInHandAction(1,uiStrings.TEXT[0],false,false, card -> card.type == CardType.ATTACK, List -> {
            AbstractCard cardtomake = List.get(0).makeStatEquivalentCopy();
            atb(new ApplyCardModifierAction(cardtomake, new ExhaustMod()));
            if (upgraded){
                cardtomake.upgrade();
            }
            atb(new MakeTempCardInHandAction(cardtomake,2));
        }));
        atb(new DamageAction(p,new DamageInfo(p,magicNumber, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    public void upp() {
    }
}