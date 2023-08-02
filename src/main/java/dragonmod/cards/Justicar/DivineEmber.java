package dragonmod.cards.Justicar;

import basemod.cardmods.EtherealMod;
import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.colorless.Madness;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.SCVExaltCardmod;
import dragonmod.actions.ExaltAction;
import dragonmod.powers.Dragonkin.DivineConvictionpower;
import dragonmod.util.TypeEnergyHelper;

public class DivineEmber extends AbstractHolyCard {

    public static final String ID = DivineEmber.class.getSimpleName();
    public AbstractCard BurnedCard;
    public DivineEmber(AbstractCard burnedCard) {
        super(ID, 1,CardType.STATUS,CardRarity.SPECIAL,CardTarget.SELF,true);
        setMagic(3,-1);
        setMagic2(2,-1);
        energyCosts.put(TypeEnergyHelper.Mana.Exalt,SecondMagicNumber);
        CardModifierManager.addModifier(this,new SCVExaltCardmod());
        if (burnedCard != null){
            BurnedCard = burnedCard;
            cardsToPreview = burnedCard.makeSameInstanceOf();
            CardModifierManager.addModifier(cardsToPreview,new EtherealMod());
            CardModifierManager.addModifier(cardsToPreview,new ExhaustMod());
        } else {
            System.out.println("Mod Soup Detected : Activating Failsafe");
            BurnedCard = new Madness();
            cardsToPreview = BurnedCard.makeSameInstanceOf();
            CardModifierManager.addModifier(cardsToPreview,new EtherealMod());
            CardModifierManager.addModifier(cardsToPreview,new ExhaustMod());
        }
    }
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (AbstractDungeon.player.hasPower(DivineConvictionpower.POWER_ID) && (AbstractDungeon.player.getPower(DivineConvictionpower.POWER_ID).amount >= energyCosts.get(TypeEnergyHelper.Mana.Exalt))) {
            this.glowColor = Color.GOLDENROD.cpy();
        }
    }
    public DivineEmber() {
        this(null);
    }
    public void applyPowers() {
        if (BurnedCard != null){
            rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
            String[] words = BurnedCard.name.split(" ");
            for (int i = 0; i < words.length; ++i) {
                rawDescription += " *" + words[i];
            }
            rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
            initializeDescription();
            initializeDescription();
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(p, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        addToBot(new ExaltAction(energyCosts.get(TypeEnergyHelper.Mana.Exalt),energyCosts,()-> new AbstractGameAction(){
            @Override
            public void update() {
                AbstractCard restorecard = cardsToPreview.makeStatEquivalentCopy();
                addToBot(new MakeTempCardInHandAction(restorecard));
                isDone = true;
            }
        }));
        super.use(p,m);
    }

    @Override
    public AbstractCard makeCopy() {
        return new DivineEmber(BurnedCard);
    }
}