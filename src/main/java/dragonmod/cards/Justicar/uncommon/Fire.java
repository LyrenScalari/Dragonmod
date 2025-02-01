package dragonmod.cards.Justicar.uncommon;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import dragonmod.CardMods.SCVExaltCardmod;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.interfaces.onExaltPower;
import dragonmod.powers.Dragonkin.InspirationPower;
import dragonmod.powers.Dragonkin.Scorchpower;
import dragonmod.util.TypeEnergyHelper;
import dragonmod.util.Wiz;

public class Fire extends AbstractJusticarCard {
    public static final String ID = Fire.class.getSimpleName();
    public Fire(){
        super(ID,1,CardType.SKILL,CardRarity.UNCOMMON,CardTarget.ENEMY);
        setMagic(5,5);
        setDamage(10,2);
        setMagic2(3);
        setCustomVar("BRN",5,2);
        setVarCalculation("BRN", (m, base) -> {
            int tmp = base;
            if (AbstractDungeon.player != null){
                AbstractPower Zeal = Wiz.Player().getPower(InspirationPower.POWER_ID);
                if (Zeal != null) {
                    tmp += Zeal.amount;
                    if (Zeal.amount >= energyCosts.get(TypeEnergyHelper.Mana.Exalt)) {
                        tmp += SecondMagicNumber;
                    }
                }
            }
            return tmp;
        });
        energyCosts.put(TypeEnergyHelper.Mana.Exalt,5);
        CardModifierManager.addModifier(this,new SCVExaltCardmod());
    }
    public void triggerOnGlowCheck() {
        if (AbstractDungeon.player.hasPower(InspirationPower.POWER_ID)) {
            if (AbstractDungeon.player.getPower(InspirationPower.POWER_ID).amount >= energyCosts.get(TypeEnergyHelper.Mana.Exalt)){
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR;
            } else if (AbstractDungeon.player.getPower(InspirationPower.POWER_ID).amount >= 2) {
                this.glowColor = Color.ORANGE.cpy();
            } else {
                this.glowColor = Color.RED.cpy();
            }
        } else {
            this.glowColor = Color.RED.cpy();
        }
    }
    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        AbstractPower Zeal = Wiz.Player().getPower(InspirationPower.POWER_ID);
        if (Zeal != null){
           if (AbstractDungeon.player.getPower(InspirationPower.POWER_ID).amount >= 5) {
               baseDamage += magicNumber;
           }
        }
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        AbstractPower Zeal = Wiz.Player().getPower(InspirationPower.POWER_ID);
        if (Zeal != null){
            if (AbstractDungeon.player.getPower(InspirationPower.POWER_ID).amount >= 2) {
                name = cardStrings.EXTENDED_DESCRIPTION[0];
                type = CardType.ATTACK;
            }
            if (Zeal.amount >= energyCosts.get(TypeEnergyHelper.Mana.Exalt)) {
                baseDamage += magicNumber;
                name = cardStrings.EXTENDED_DESCRIPTION[1];
            }
            if (Zeal.amount < 2) {
                name = cardStrings.NAME;
                type = CardType.SKILL;
            }
        } else {
            name = cardStrings.NAME;
            type = CardType.SKILL;
        }
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToEnemy(m,new Scorchpower(m,p,customVar("BRN")));
        if (type == CardType.ATTACK){
            Wiz.dmg(m,new DamageInfo(p,baseDamage, DamageInfo.DamageType.NORMAL));
            Wiz.atb(new AbstractGameAction() {
                @Override
                public void update() {
                    for (AbstractPower p : AbstractDungeon.player.powers){
                        if (p instanceof onExaltPower){
                            ((onExaltPower) p).triggerOnExalt();
                        }
                    }
                    for (AbstractRelic p : AbstractDungeon.player.relics){
                        if (p instanceof onExaltPower){
                            ((onExaltPower) p).triggerOnExalt();
                        }
                    }
                    isDone = true;
                }
            });
        }
    }
}