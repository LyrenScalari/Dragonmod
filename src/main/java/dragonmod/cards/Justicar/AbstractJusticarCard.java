package dragonmod.cards.Justicar;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DamageModifiers.HolyDamage;
import dragonmod.cards.AbstractDragonCard;
import dragonmod.characters.TheJusticar;


public abstract class AbstractJusticarCard extends AbstractDragonCard {
    public AbstractJusticarCard(final String id,
                                final int cost,
                                final AbstractCard.CardType type,
                                final AbstractCard.CardRarity rarity,
                                final AbstractCard.CardTarget target) {

        super(id, cost, type, TheJusticar.Enums.Justicar_Red_COLOR, rarity, target);

    }
    public AbstractJusticarCard(final String id,
                                final int cost,
                                final AbstractCard.CardType type,
                                final AbstractCard.CardRarity rarity,
                                final AbstractCard.CardTarget target, boolean colorless) {

        super(id, cost, type, CardColor.COLORLESS, rarity, target);

    }
    public AbstractDamageModifier HolyDamage = new HolyDamage(true);
    public boolean splitdamage = false;
    public int realBaseDamage;
    public int realBaseMagic;
    private boolean needsArtRefresh = false;
    public Color renderColor = Color.WHITE.cpy();
    public boolean freeManaOnce = false;
    @Override
    public void applyPowers() {
        if (splitdamage) {
            DamageModifierManager.removeModifier(this, HolyDamage);
            super.applyPowers();
            this.BaseSecondMagicNumber = this.baseDamage;
            this.SecondMagicNumber = this.damage;
            this.isSecondMagicNumberModified = this.isDamageModified;
            DamageModifierManager.addModifier(this, HolyDamage);
            super.applyPowers();
        }
        super.applyPowers();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        if (splitdamage) {
            DamageModifierManager.removeModifier(this, HolyDamage);
            super.calculateCardDamage(mo);
            this.BaseSecondMagicNumber = this.baseDamage;
            this.SecondMagicNumber = this.damage;
            this.isSecondMagicNumberModified = this.isDamageModified;
            DamageModifierManager.addModifier(this, HolyDamage);
            super.calculateCardDamage(mo);
        }
        super.calculateCardDamage(mo);
    }
}
