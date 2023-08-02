package dragonmod.cards.Justicar;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import dragonmod.CardMods.SCVExaltCardmod;
import dragonmod.actions.ExaltAction;
import dragonmod.actions.SmiteAction;
import dragonmod.powers.Dragonkin.DivineConvictionpower;
import dragonmod.util.CustomTags;
import dragonmod.util.TypeEnergyHelper;

public class HolySmite extends AbstractHolyCard {

    public static final String ID = HolySmite.class.getSimpleName(); // USE THIS ONE FOR THE TEMPLATE;
    public HolySmite() {
        super(ID,2,CardType.ATTACK,CardRarity.BASIC,CardTarget.ENEMY);
        setDamage(6,2);
        setMagic(2);
        setMagic2(2);
        tags.add(CustomTags.Smite);
        energyCosts.put(TypeEnergyHelper.Mana.Exalt,SecondMagicNumber);
        CardModifierManager.addModifier(this,new SCVExaltCardmod());
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (AbstractDungeon.player.hasPower(DivineConvictionpower.POWER_ID) && (AbstractDungeon.player.getPower(DivineConvictionpower.POWER_ID).amount >= energyCosts.get(TypeEnergyHelper.Mana.Exalt))) {
            this.glowColor = Color.GOLDENROD.cpy();
        }
    }
    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        addToBot(new SmiteAction(m, new DamageInfo(p, damage, damageTypeForTurn),false));
        addToBot(new ExaltAction(energyCosts.get(TypeEnergyHelper.Mana.Exalt),energyCosts,()-> new AbstractGameAction(){
            @Override
            public void update() {
                addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, magicNumber, false)));
                addToBot(new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, false)));
                isDone = true;
            }
        }));
        super.use(p,m);
    }
}