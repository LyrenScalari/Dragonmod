package dragonmod.cards.Justicar;


import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import dragonmod.CardMods.SCVExaltCardmod;
import dragonmod.DragonMod;
import dragonmod.actions.ExaltAction;
import dragonmod.powers.Dragonkin.DivineConvictionpower;
import dragonmod.powers.Dragonkin.PenancePower;
import dragonmod.util.TypeEnergyHelper;

public class ChainsOfTruth extends AbstractHolyCard {


    public static final String ID = DragonMod.makeID(ChainsOfTruth.class.getSimpleName());
    public ChainsOfTruth() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        setDamage(6,2);
        setMagic(2);
        setMagic2(2);
        this.isMultiDamage = true;
        energyCosts.put(TypeEnergyHelper.Mana.Exalt,SecondMagicNumber);
        CardModifierManager.addModifier(this,new SCVExaltCardmod());
    }
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (AbstractDungeon.player.hasPower(DivineConvictionpower.POWER_ID) && (AbstractDungeon.player.getPower(DivineConvictionpower.POWER_ID).amount >= energyCosts.get(TypeEnergyHelper.Mana.Exalt))) {
            this.glowColor = Color.GOLDENROD.cpy();
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p,baseDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.LIGHTNING));

        for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters){
                addToBot(new ApplyPowerAction(mo,p,new PenancePower(mo,p,magicNumber)));
        }

        addToBot(new ExaltAction(energyCosts.get(TypeEnergyHelper.Mana.Exalt),energyCosts,()-> new AbstractGameAction(){
            @Override
            public void update() {
                for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters){
                    addToBot(new ApplyPowerAction(mo,p, new VulnerablePower(mo,magicNumber,false)));
                }
                isDone = true;
            }
        }));
        super.use(p,m);
    }
}