package dragonmod.cards.Justicar;


import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.SCVExaltCardmod;
import dragonmod.actions.ExaltAction;
import dragonmod.actions.SmiteAction;
import dragonmod.powers.Dragonkin.DivineConvictionpower;
import dragonmod.powers.Dragonkin.PenancePower;
import dragonmod.util.TypeEnergyHelper;


public class DivineJolt extends AbstractHolyCard {

    public static final String ID = DivineJolt.class.getSimpleName();
    public DivineJolt(){
        super(ID,1,CardType.ATTACK,CardRarity.COMMON,CardTarget.ALL_ENEMY);
    setDamage(5,2);
    setMagic(3);
    setMagic2(2);
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
        for (int i = 0; i < 2; ++i) {
           m = AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng);
           if (m != null) {
               addToBot(new SmiteAction(m, new DamageInfo(p, damage, damageTypeForTurn)));
               AbstractMonster finalM = m;
               addToBot(new ExaltAction(energyCosts.get(TypeEnergyHelper.Mana.Exalt),energyCosts,()-> new AbstractGameAction(){
                   @Override
                   public void update() {
                       addToBot(new ApplyPowerAction(finalM, p, new PenancePower(finalM, p, magicNumber)));
                       isDone = true;
                   }
               }));
           } else {
               m = AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng);
               addToBot(new SmiteAction(m, new DamageInfo(p, damage, damageTypeForTurn)));
               AbstractMonster finalM = m;
               addToBot(new ExaltAction(energyCosts.get(TypeEnergyHelper.Mana.Exalt),energyCosts,()-> new AbstractGameAction(){
                   @Override
                   public void update() {
                       addToBot(new ApplyPowerAction(finalM, p, new PenancePower(finalM, p, magicNumber)));
                       isDone = true;
                   }
               }));
           }

        }
        super.use(p,m);
    }
}